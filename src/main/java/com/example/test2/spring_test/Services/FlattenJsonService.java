package com.example.test2.spring_test.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.test2.spring_test.Models.classes.EmailDetails;
import com.example.test2.spring_test.Models.classes.FlattenedJson;
import com.example.test2.spring_test.Models.classes.FlattenedRecord;
import com.example.test2.spring_test.Repositories.FlattenJsonRepository;
import com.example.test2.spring_test.Repositories.FlattenedRecordRepository;
import com.example.test2.spring_test.Services.Email.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;

import lombok.AllArgsConstructor;
import static org.toilelibre.libe.curl.Curl.curl;

@AllArgsConstructor
@Service
public class FlattenJsonService {

    @Autowired
    private final FlattenJsonRepository flattenJsonRepository;
    @Autowired
    private final FlattenedRecordRepository flattenRecordRepository;
    @Autowired
    private final EmailServiceImpl emailService;

    public ResponseEntity<String> flatten(String email, String body) {
        try {

            var result = curl(body);
            if (result == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            int statusCode = result.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            String jsonResult = new BasicResponseHandler().handleResponse(result);
            Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(jsonResult);
            List<FlattenedRecord> recordsToAdd = new ArrayList<FlattenedRecord>();
            flattenJson.forEach((k, value) -> {
                var oldRecord = flattenRecordRepository.findByKey((String) k);
                FlattenedRecord flattenRecord;

                if (oldRecord.isPresent()) {
                    if (value instanceof String) {
                        oldRecord.get().getValues().add((String) value);
                    }
                    flattenRecord = oldRecord.get();

                } else {
                    flattenRecord = new FlattenedRecord();
                    flattenRecord.setKey(k);
                    if (value instanceof String) {
                        List<String> l = new ArrayList<String>();
                        l.add((String) value);
                        flattenRecord.setValues(l);
                    }
                }

                var res = flattenRecordRepository.save(flattenRecord);
                var r = new FlattenedRecord();
                r.setId(res.getId());
                recordsToAdd.add(r);
            });
            FlattenedJson flattenedJson = new FlattenedJson();
            flattenedJson.setRecords(recordsToAdd);
            var res = flattenJsonRepository.save(flattenedJson);
            res = flattenJsonRepository.findById(res.getId()).get();
            // return new ResponseEntity<>(res, HttpStatus.OK);
            ObjectMapper mapper = new ObjectMapper();
            return new ResponseEntity<>(sendEmail(email, mapper.writeValueAsString(res)), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String sendEmail(String email, String body) {
        EmailDetails details = new EmailDetails(email, body, "Flattened Json", null);
        return emailService.sendSimpleMail(details);
        // return body;
    }
}
