package com.example.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

public class CustomerClient {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/myrestaurant/customer");
        CloseableHttpResponse response = client.execute(httpGet);
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()){
            sb.append(sc.nextLine());
            sb.append("\n");

        }
        response.close();
        client.close();
        System.out.println(sb.toString());

    }

    /**
     * Read the response and convert it into string
     * @param response response from http request
     * @return string of the response
     * @throws IOException
     */
    public static String readResponse(CloseableHttpResponse response) throws IOException {
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()){
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        response.close();
        return sb.toString();
    }

    /**
     * Get the list of customers by calling the API
     * @return string representation of all the customers currently in the restaurant
     */
    private static String getCustomers() {
        // The client that will be used to execute HTTP requests
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Get the request object to be executed by the client
            HttpGet httpGet = new HttpGet("http://localhost:8080/myrestaurant/customer");
            // The execution of the request. A response object will be returned
            CloseableHttpResponse response = client.execute(httpGet);
            // Read the response and convert it to string
            return readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get customers";
        }
    }

    /**
     * Gets a particular customer by id
     * @param id of the customer
     * @return string representation of the customer
     */
    private static String getACustomer(int id) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format(""));
            CloseableHttpResponse response = client.execute(httpGet);
            return readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get a customer";
        }
    }

    private static void createCustomer(String name, int age) {
        try(CloseableHttpClient client =  HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/myrestaurant/customer/%s/%d", name, age));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void replaceCustomer(int id, String name, int age) {
        try(CloseableHttpClient client =  HttpClients.createDefault()){
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/myrestaurant/customer/%s/%d", name, age));
            CloseableHttpResponse httpResponse = client.execute(httpPut);
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer(int id) {
        try(CloseableHttpClient client =  HttpClients.createDefault()){
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/myrestaurant/customer/%d", id));
            CloseableHttpResponse deleteResponse = client.execute(httpDelete);
            deleteResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
