package tw.com.wd;


import com.google.gson.Gson;

import java.util.List;
import java.util.Map;


public class Executor {
    public static void main(String[] args) {
//        Domain domain = new Domain();
//        Domain domain2 = new Domain();
//
//        domain.setS("<p>test</p>?q=123");
//        domain.setD(1.23f);
//        domain.setI(456);
//        domain.setL(789L);
//        domain.setSs(new String[] {"A", "B", "C"});
//
//        domain2.setS("<p>test</p>?q=123");
//        domain2.setD(1.23f);
//        domain2.setI(456);
//        domain2.setL(789L);
//        domain2.setSs(new String[] {"A", "B", "C"});
//
//        List<String> sList = new ArrayList<>();
//        sList.add("Q");
//        sList.add("W");
//        sList.add("E");
//        domain.setsList(sList);
//        domain2.setsList(sList);
//
//        Map<String, String> sMap = new HashMap<>();
//        sMap.put("K1", "V1");
//        sMap.put("K2", "V2");
//        sMap.put("K3", "V3");
//        domain.setsMap(sMap);
//        domain2.setsMap(sMap);
//
//        System.out.println(new GsonBuilder().create().toJson(domain));
//        System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(domain));
//        System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(domain));
//        System.out.println(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(domain));
//        System.out.println(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(domain));
//
//        List<Domain> domainList = new ArrayList<>();
//        domainList.add(domain);
//        domainList.add(domain2);
//
//        System.out.println(new Gson().toJson(domainList));

        String json = "{\"s\":123,\"l\":789,\"i\":456,\"d\":1.2300000190734863,\"ss\":[\"A\",\"B\",\"C\"],\"sList\":[\"Q\",\"W\",\"E\"],\"sMap\":{\"K1\":\"V1\",\"K2\":\"V2\",\"K3\":\"V3\"}}";
        String json2= "{\"s\":123,\"l\":789,\"i\":456,\"d\":1.2300000190734863,\"ss\":[\"A\",\"B\",\"C\"],\"ssList\":\"ssList\",\"sListE\":\"sListE\",\"sMap\":{\"K1\":\"V1\",\"K2\":\"V2\",\"K3\":\"V3\"}}";

        System.out.println("JSON: " + json);
        Domain domain3 = new Gson().fromJson(json, Domain.class);
        System.out.println(domain3.getS());

        System.out.println("JSON: " + json2);
        Domain domain4 = new Gson().fromJson(json2, Domain.class);
        System.out.println(domain4.getsList());
        System.out.println(domain4.getsMap().get("K1"));
    }

    public static class Domain {
        private String s;
        private Long l;
        private Integer i;
        private Double d;
        private String[] ss;
        private List<String> sList;
        private Map<String, String> sMap;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public long getL() {
            return l;
        }

        public void setL(long l) {
            this.l = l;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public double getD() {
            return d;
        }

        public void setD(double d) {
            this.d = d;
        }

        public String[] getSs() {
            return ss;
        }

        public void setSs(String[] ss) {
            this.ss = ss;
        }

        public List<String> getsList() {
            return sList;
        }

        public void setsList(List<String> sList) {
            this.sList = sList;
        }

        public Map<String, String> getsMap() {
            return sMap;
        }

        public void setsMap(Map<String, String> sMap) {
            this.sMap = sMap;
        }
    }
}