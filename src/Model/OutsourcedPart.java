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
public class OutsourcedPart extends Part {
    private String companyName;

    OutsourcedPart() {
        // ***TO-DO: Complete constructor***
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getCompanyName() {
        return companyName;
    }
}
