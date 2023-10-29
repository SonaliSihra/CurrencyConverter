package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        // String[] currencyArr = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SSP", "SRD", "STD", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMK", "ZMW", "ZWL"};
        Map<String, Object> symbolsList = symbolsNames();
        String[] currencyArr = concatenateSymbols(symbolsList);


        JFrame f = new JFrame("Currency Converter");
        f.setVisible(true);
        f.setSize(500, 400);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel fromCur = new JLabel("<html><br/> Choose from currency: </html>");
        fromCur.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        f.add(fromCur);

        JComboBox cb1 = new JComboBox(currencyArr);
        cb1.setBounds(100, 100, 400, 30);
        f.add(cb1);

        JLabel toCur = new JLabel("<html><br/> Choose to currency: </html>");
        toCur.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel amount = new JLabel("<html><br/> Enter amount for conversion: </html>");
        amount.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        amount.setVisible(true);

        JTextField amountField = new JTextField("                                                               ");
        amountField.setBounds(200, 200, 200, 30);
        amountField.setVisible(true);

        JComboBox cb2 = new JComboBox(currencyArr);
        cb2.setBounds(150, 150, 400, 30);
        f.add(cb1);
        f.add(toCur);
        f.add(cb2);

        f.add(amount);
        f.add(amountField);

        JButton convertButton = new JButton("<html>Click to Convert </html>");
        convertButton.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(convertButton);


        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());

                String toCur = cb2.getSelectedItem().toString().substring(0, 3);
                String fromCur = cb1.getSelectedItem().toString().substring(0, 3);

                try {
                    double conversionRate = getConversionRate(toCur, fromCur);
                    final DecimalFormat decfor = new DecimalFormat("0.00");

                    JOptionPane.showMessageDialog(null, "answer : " + decfor.format(conversionRate * amount));
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    private static double getConversionRate(String toCur, String fromCur) throws JsonProcessingException {

        double rate;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://data.fixer.io/api/latest?access_key=c8cfd0d4f8d76a1c20e8b8596850cdbf&symbols=" + fromCur + "," + toCur))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
// convert JSON string to Map
        JsonNode jsonNode = mapper.readTree(response.body()).get("rates");

        System.out.println("jsonNode" + jsonNode);

        Map<String, Double> map = mapper.convertValue(jsonNode, Map.class);

        if (Objects.equals(fromCur, "EUR") || Objects.equals(toCur, "EUR")) {
            Double toCurd = Double.valueOf(map.get(toCur).toString());
            Double fromCurD = Double.valueOf(map.get(fromCur).toString());
            rate = toCurd / fromCurD;
        } else {
            rate = map.get(toCur) / map.get(fromCur);
        }
        return rate;
    }

    private static String[] concatenateSymbols(Map<String, Object> symbolsList) {

        String[] targetArray = new String[symbolsList.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : symbolsList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            targetArray[i] = entry.getKey() + " " + entry.getValue();
            i++;
        }
        return targetArray;
    }

    private static Map<String, Object> symbolsNames() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
// convert JSON string to Map
        JsonNode jsonNode = mapper.readTree(getSymbols()).get("symbols");

        System.out.println("jsonNode" + jsonNode);

        Map<String, Object> map = mapper.convertValue(jsonNode, Map.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return map;
    }

    public static String getSymbols() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://data.fixer.io/api/symbols?access_key=c8cfd0d4f8d76a1c20e8b8596850cdbf"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.body();
    }
}