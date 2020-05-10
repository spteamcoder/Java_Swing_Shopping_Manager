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
public class Position {

    private String id;

    private String Position;

    private String Payroll;

    public Position(String id, String Position, String Payroll) {
        this.id = id;
        this.Position = Position;
        this.Payroll = Payroll;
    }

    public Position() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getPayroll() {
        return Payroll;
    }

    public void setPayroll(String Payroll) {
        this.Payroll = Payroll;
    }

    @Override
    public String toString() {
        return "Position{" + "id=" + id + ", Position=" + Position + ", Payroll=" + Payroll + '}';
    }

}
