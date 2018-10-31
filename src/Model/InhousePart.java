/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jnsch
 */
public class InhousePart extends Part {
    private int machineID;
    
    public InhousePart() {
        super();
        this.setMachineID(0);
    }
    
    public InhousePart(String name, double price, int inStock, int min, int max, int machineID) {
        super(name, price, inStock, min, max);
        this.setMachineID(machineID);
    }
    
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
    public int getMachineID() {
        return machineID;
    }
}
