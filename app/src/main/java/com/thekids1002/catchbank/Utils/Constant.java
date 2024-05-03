package com.thekids1002.catchbank.Utils;

import java.util.ArrayList;

public class Constant {

    public static final String DEFAULT_HOST = "https://api.emaxdentallab.vn/" ;
    public static Constant gI;

    public static Constant gI(){
        if(gI == null){
            gI = new Constant();
        }
        return gI;
    }
    public  ArrayList<String> listBankPackage;

    public Constant() {
        this.listBankPackage = new ArrayList<>();
        this.listBankPackage.add("com.VCB");
        this.listBankPackage.add("mobile.acb.com.vn");
    }


}
