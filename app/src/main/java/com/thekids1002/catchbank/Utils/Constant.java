package com.thekids1002.catchbank.Utils;

import java.util.HashMap;

public class Constant {

    public static final String DEFAULT_HOST = "https://api.emaxdentallab.vn/" ;
    public static Constant gI;

    public static Constant gI(){
        if(gI == null){
            gI = new Constant();
        }
        return gI;
    }
    // Danh sách các bank hỗ trợ
    public HashMap<String,String> mapSupportBank;

    public Constant() {
        this.mapSupportBank = new HashMap<>();
        this.mapSupportBank.put("com.VCB","VCB");
        this.mapSupportBank.put("mobile.acb.com.vn","ACB");
    }


}
