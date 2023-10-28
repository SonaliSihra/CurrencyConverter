package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.POJO.Symbols.Symbols;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String[] currencyArr = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SSP", "SRD", "STD", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMK", "ZMW", "ZWL"};
        String[] symbolsList = ConcatenateSymbols(currencyArr);


        JFrame f = new JFrame("Currency Converter");
        f.setVisible(true);
        f.setSize(500, 400);
        f.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel fromCur = new JLabel("<html><br/> Choose from currency: </html>");
        fromCur.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        f.add(fromCur);

        JComboBox cb1 = new JComboBox(symbolsList);
        cb1.setBounds(100, 100, 400, 30);
        f.add(cb1);

        JLabel toCur = new JLabel("<html><br/> Choose to currency: </html>");
        toCur.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel amount = new JLabel("<html><br/> Enter amount for conversion: </html>");
        amount.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        amount.setVisible(true);

        JTextField amountField = new JTextField("                                                                       ");
        amountField.setBounds(200, 200, 500, 30);
        amountField.setVisible(true);

        JComboBox cb2 = new JComboBox(symbolsList);
        cb2.setBounds(150, 150, 400, 30);
        f.add(cb1);
        f.add(toCur);
        f.add(cb2);

        f.add(amount);
        f.add(amountField);

        JButton convertButton = new JButton("<html>Click to Convert </html>");
        convertButton.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(convertButton);
    }

    private static String[] ConcatenateSymbols(String[] currencyArr) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Symbols symbols = objectMapper.readValue(getSymbols(), Symbols.class);

        String[] symbolsConList = new String[currencyArr.length];
        HashMap<String, String> hs = new HashMap<>();
        hs.put("aed", symbols.getSymbols().getAed());
        hs.put("afn", symbols.getSymbols().getAfn());
        hs.put("all", symbols.getSymbols().getAll());
        hs.put("amd", symbols.getSymbols().getAmd());
        hs.put("ang", symbols.getSymbols().getAng());
        hs.put("aoa", symbols.getSymbols().getAoa());
        hs.put("ars", symbols.getSymbols().getArs());
        hs.put("aud", symbols.getSymbols().getAud());
        hs.put("awg", symbols.getSymbols().getAwg());
        hs.put("azn", symbols.getSymbols().getAzn());
        hs.put("bam", symbols.getSymbols().getBam());
        hs.put("bbd", symbols.getSymbols().getBbd());
        hs.put("bdt", symbols.getSymbols().getBdt());
        hs.put("bgn", symbols.getSymbols().getBgn());
        hs.put("bhd", symbols.getSymbols().getBhd());
        hs.put("bif", symbols.getSymbols().getBif());
        hs.put("bmd", symbols.getSymbols().getBmd());
        hs.put("bnd", symbols.getSymbols().getBnd());
        hs.put("bob", symbols.getSymbols().getBob());
        hs.put("brl", symbols.getSymbols().getBrl());
        hs.put("bsd", symbols.getSymbols().getBsd());
        hs.put("btc", symbols.getSymbols().getBtc());
        hs.put("btn", symbols.getSymbols().getBtn());
        hs.put("bwp", symbols.getSymbols().getBwp());
        hs.put("byn", symbols.getSymbols().getByn());
        hs.put("byr", symbols.getSymbols().getByr());
        hs.put("bzd", symbols.getSymbols().getBzd());
        hs.put("cad", symbols.getSymbols().getCad());
        hs.put("cdf", symbols.getSymbols().getCdf());
        hs.put("chf", symbols.getSymbols().getChf());
        hs.put("clf", symbols.getSymbols().getClf());
        hs.put("clp", symbols.getSymbols().getClp());
        hs.put("cny", symbols.getSymbols().getCny());
        hs.put("cop", symbols.getSymbols().getCop());
        hs.put("crc", symbols.getSymbols().getCrc());
        hs.put("cuc", symbols.getSymbols().getCuc());
        hs.put("cup", symbols.getSymbols().getCuc());
        hs.put("cve", symbols.getSymbols().getCve());
        hs.put("czk", symbols.getSymbols().getCzk());
        hs.put("djf", symbols.getSymbols().getDjf());
        hs.put("dkk", symbols.getSymbols().getDkk());
        hs.put("dop", symbols.getSymbols().getDop());
        hs.put("dzd", symbols.getSymbols().getDzd());
        hs.put("egp", symbols.getSymbols().getEgp());
        hs.put("ern", symbols.getSymbols().getErn());
        hs.put("etb", symbols.getSymbols().getEtb());
        hs.put("eur", symbols.getSymbols().getEur());
        hs.put("fjd", symbols.getSymbols().getFjd());
        hs.put("fkp", symbols.getSymbols().getFkp());
        hs.put("gbp", symbols.getSymbols().getGbp());
        hs.put("gel", symbols.getSymbols().getGel());
        hs.put("ggp", symbols.getSymbols().getGgp());
        hs.put("ghs", symbols.getSymbols().getGhs());
        hs.put("gip", symbols.getSymbols().getGip());
        hs.put("gmd", symbols.getSymbols().getGmd());
        hs.put("gnf", symbols.getSymbols().getGnf());
        hs.put("gtq", symbols.getSymbols().getGtq());
        hs.put("gyd", symbols.getSymbols().getGyd());
        hs.put("hkd", symbols.getSymbols().getHkd());
        hs.put("hnl", symbols.getSymbols().getHnl());
        hs.put("hrk", symbols.getSymbols().getHrk());
        hs.put("htg", symbols.getSymbols().getHtg());
        hs.put("huf", symbols.getSymbols().getHuf());
        hs.put("idr", symbols.getSymbols().getIdr());
        hs.put("ils", symbols.getSymbols().getIls());
        hs.put("imp", symbols.getSymbols().getImp());
        hs.put("inr", symbols.getSymbols().getInr());
        hs.put("iqd", symbols.getSymbols().getIqd());
        hs.put("irr", symbols.getSymbols().getIrr());
        hs.put("isk", symbols.getSymbols().getIsk());
        hs.put("jep", symbols.getSymbols().getJep());
        hs.put("jmd", symbols.getSymbols().getJmd());
        hs.put("jod", symbols.getSymbols().getJod());
        hs.put("jpy", symbols.getSymbols().getJpy());
        hs.put("kes", symbols.getSymbols().getKes());
        hs.put("kgs", symbols.getSymbols().getKgs());
        hs.put("khr", symbols.getSymbols().getKhr());
        hs.put("kmf", symbols.getSymbols().getKmf());
        hs.put("kpw", symbols.getSymbols().getKpw());
        hs.put("krw", symbols.getSymbols().getKrw());
        hs.put("kwd", symbols.getSymbols().getKwd());
        hs.put("kyd", symbols.getSymbols().getKyd());
        hs.put("kzt", symbols.getSymbols().getKzt());
        hs.put("lak", symbols.getSymbols().getLak());
        hs.put("lbp", symbols.getSymbols().getLbp());
        hs.put("lkr", symbols.getSymbols().getLkr());
        hs.put("lrd", symbols.getSymbols().getLrd());
        hs.put("lsl", symbols.getSymbols().getLsl());
        hs.put("ltl", symbols.getSymbols().getLtl());
        hs.put("lvl", symbols.getSymbols().getLvl());
        hs.put("lyd", symbols.getSymbols().getLyd());
        hs.put("mad", symbols.getSymbols().getMad());
        hs.put("mdl", symbols.getSymbols().getMdl());
        hs.put("mga", symbols.getSymbols().getMga());
        hs.put("mkd", symbols.getSymbols().getMkd());
        hs.put("mmk", symbols.getSymbols().getMmk());
        hs.put("mnt", symbols.getSymbols().getMnt());
        hs.put("mop", symbols.getSymbols().getMop());
        hs.put("mro", symbols.getSymbols().getMro());
        hs.put("mur", symbols.getSymbols().getMur());
        hs.put("mvr", symbols.getSymbols().getMvr());
        hs.put("mwk", symbols.getSymbols().getMwk());
        hs.put("mxn", symbols.getSymbols().getMxn());
        hs.put("myr", symbols.getSymbols().getMyr());
        hs.put("mzn", symbols.getSymbols().getMzn());
        hs.put("nad", symbols.getSymbols().getNad());
        hs.put("ngn", symbols.getSymbols().getNgn());
        hs.put("nio", symbols.getSymbols().getNio());
        hs.put("nok", symbols.getSymbols().getNok());
        hs.put("npr", symbols.getSymbols().getNpr());
        hs.put("nzd", symbols.getSymbols().getNzd());
        hs.put("omr", symbols.getSymbols().getOmr());
        hs.put("pab", symbols.getSymbols().getPab());
        hs.put("pen", symbols.getSymbols().getPen());
        hs.put("pgk", symbols.getSymbols().getPgk());
        hs.put("php", symbols.getSymbols().getPhp());
        hs.put("pkr", symbols.getSymbols().getPkr());
        hs.put("pln", symbols.getSymbols().getPln());
        hs.put("pyg", symbols.getSymbols().getPyg());
        hs.put("qar", symbols.getSymbols().getQar());
        hs.put("ron", symbols.getSymbols().getRon());
        hs.put("rsd", symbols.getSymbols().getRsd());
        hs.put("rub", symbols.getSymbols().getRub());
        hs.put("rwf", symbols.getSymbols().getRwf());
        hs.put("sar", symbols.getSymbols().getSar());
        hs.put("sbd", symbols.getSymbols().getSbd());
        hs.put("scr", symbols.getSymbols().getScr());
        hs.put("sdg", symbols.getSymbols().getSdg());
        hs.put("sek", symbols.getSymbols().getSek());
        hs.put("sgd", symbols.getSymbols().getSgd());
        hs.put("shp", symbols.getSymbols().getShp());
        hs.put("sle", symbols.getSymbols().getSle());
        hs.put("sll", symbols.getSymbols().getSll());
        hs.put("sos", symbols.getSymbols().getSos());
        hs.put("srd", symbols.getSymbols().getSrd());
        hs.put("ssp", symbols.getSymbols().getSsp());
        hs.put("std", symbols.getSymbols().getStd());
        hs.put("svc", symbols.getSymbols().getSvc());
        hs.put("syp", symbols.getSymbols().getSyp());
        hs.put("szl", symbols.getSymbols().getSzl());
        hs.put("thb", symbols.getSymbols().getThb());
        hs.put("tjs", symbols.getSymbols().getTjs());
        hs.put("tmt", symbols.getSymbols().getTmt());
        hs.put("tnd", symbols.getSymbols().getTnd());
        hs.put("top", symbols.getSymbols().getTop());
        hs.put("try", symbols.getSymbols().getTry());
        hs.put("ttd", symbols.getSymbols().getTtd());
        hs.put("twd", symbols.getSymbols().getTwd());
        hs.put("tzs", symbols.getSymbols().getTzs());
        hs.put("uah", symbols.getSymbols().getUah());
        hs.put("ugx", symbols.getSymbols().getUgx());
        hs.put("usd", symbols.getSymbols().getUsd());
        hs.put("uyu", symbols.getSymbols().getUyu());
        hs.put("uzs", symbols.getSymbols().getUzs());
        hs.put("vef", symbols.getSymbols().getVef());
        hs.put("ves", symbols.getSymbols().getVes());
        hs.put("vnd", symbols.getSymbols().getVnd());
        hs.put("vuv", symbols.getSymbols().getVuv());
        hs.put("wst", symbols.getSymbols().getWst());
        hs.put("xaf", symbols.getSymbols().getXaf());
        hs.put("xag", symbols.getSymbols().getXag());
        hs.put("xau", symbols.getSymbols().getXau());
        hs.put("xcd", symbols.getSymbols().getXcd());
        hs.put("xdr", symbols.getSymbols().getXdr());
        hs.put("xof", symbols.getSymbols().getXof());
        hs.put("xpf", symbols.getSymbols().getXpf());
        hs.put("yer", symbols.getSymbols().getYer());
        hs.put("zar", symbols.getSymbols().getZar());
        hs.put("zmk", symbols.getSymbols().getZmk());
        hs.put("zmw", symbols.getSymbols().getZmk());
        hs.put("zwl", symbols.getSymbols().getZwl());

        for (int i = 0; i < symbolsConList.length; i++) {
            symbolsConList[i] = currencyArr[i] + " " + hs.get(currencyArr[i].toLowerCase());
        }
        return symbolsConList;
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