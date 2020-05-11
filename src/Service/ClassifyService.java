/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Classify;
import java.util.List;

/**
 *
 * @author VLT
 */
public interface ClassifyService {

    List<Classify> getListClassify();

    boolean findClasstifyById(String id);
    
    void insertClasstify(Classify c);
}
