package tw.com.wd;


import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Executor {
    public static void main(String[] args) {
        Domain domain = new Domain();

        domain.setS("<p>test</p>?q=123");
        //domain.setD(1.23f);
        //domain.setI(456);
        //domain.setL(789L);
        domain.setSs(new String[] {"A", "B", "C"});

        List<String> sList = new ArrayList<>();
        sList.add("Q");
        sList.add("W");
        sList.add("E");
        domain.setsList(sList);

        Map<String, String> sMap = new HashMap<>();
        sMap.put("K1", "V1");
        sMap.put("K2", "V2");
        sMap.put("K3", "V3");
        domain.setsMap(sMap);

        System.out.println(new GsonBuilder().create().toJson(domain));
        System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(domain));
        System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(domain));
        System.out.println(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(domain));
        System.out.println(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(domain));
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