package com.showoff.ie.integration.test.utils;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.DataTable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


import static com.showoff.ie.integration.test.utils.ConstantsUtil.*;
import static org.assertj.core.api.Assertions.fail;


public class ApiUtil {

    private static final String NULL_VALUE = null;

    /**
     * Constructs the header parameters of the API
     *
     * @return Key-value pair of headers parameters in Map
     */
    public Map<String, String> getValidHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return headers;
    }

    /**
     * Returns the status code of the corresponding HttpsStatus
     * @param code http code
     * @return the status code of the http status
     */
//    public int getStatusCode(String code) {
//          return HttpStatus.valueOf(Integer.parseInt(code.substring(5,8))).value);
//       }

    /**
     * Returns the dataTable data in the form of Map
     *
     * @param table dataTable
     * @return dataTable data in Map
     */
    public Map<String, String> returnMap(DataTable table) {
        Map<String, String> dataMap = new HashMap<>();
        List<List<String>> UserDetails = table.raw();
        for (List<String> detail : UserDetails) {
            dataMap.put(detail.get(0), detail.get(1));
        }
        return dataMap;
    }


    /**
     * This implementation method updates the existing json file content with map of data passed to
     * the method
     *
     * @param detailsMap a map of data with new request values
     * @param fileName   name of the request file
     * @return updated json file data in String
     */

    public String getRequestWithNewValueToTheField(Map<String, String> detailsMap, String fileName) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(JSON_REQUEST_PATH + fileName + ".json"));
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            //read JSON like DOM Parser
            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode userInfoNode = rootNode.path("user");
            if(detailsMap.containsKey("email")){
                ((ObjectNode) userInfoNode).put("email", detailsMap.get("email"));
            }
            if(detailsMap.containsKey("first_name")){
                ((ObjectNode) userInfoNode).put("first_name", detailsMap.get("first_name"));
            }
            if(detailsMap.containsKey("last_name")){
                ((ObjectNode) userInfoNode).put("last_name", detailsMap.get("last_name"));
            }
            if(detailsMap.containsKey("last_name")){
                ((ObjectNode) userInfoNode).put("password", detailsMap.get("password"));
            } else if (detailsMap.isEmpty()){
                System.out.println("testinf 123");
            }
            return rootNode.toString();
        } catch (IOException exe) {
           fail("Exception occured", exe);
        }
        return null;
    }

    public Map<String, String> generateHeaders(DataTable table) {
        Map<String, String> validHeaders = getValidHeaders();

        List<List<String>> headerTable = table.raw();

        for (List<String> header : headerTable) {
            validHeaders.replace(header.get(0),header.get(1));
        }
        return validHeaders;
    }

    private void updateMapWithNewValue(String keyToBeUpdated, String newValue, Map map) {
        if (keyToBeUpdated.equals(FIRST_NAME) || keyToBeUpdated.equals(LAST_NAME)
                || keyToBeUpdated.equals(EMAIL_ID)) {
            map.put(keyToBeUpdated,newValue);

        }
    }


}
