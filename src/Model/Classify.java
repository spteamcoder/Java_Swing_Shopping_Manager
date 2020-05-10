/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author VLT
 */
public class Classify {

    private String id;

    private String Classify;


    public Classify(String id, String Classify) {
        this.id = id;
        this.Classify = Classify;
    }

    public Classify() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassify() {
        return Classify;
    }

    public void setClassify(String Classify) {
        this.Classify = Classify;
    }
}
