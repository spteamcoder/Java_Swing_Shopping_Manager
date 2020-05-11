/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import Model.Position;
import java.util.List;

/**
 *
 * @author VLT
 */
public interface PositionService {
    List<Position> getListPosition();
    
    boolean findPositionById(String id);
    
    void insertPosition(Position position);
}
