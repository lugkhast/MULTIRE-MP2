/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multire.mp1.search.hrcc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lugkhast
 */
public class CoherenceCoord {

    public int x;
    public int y;
    public CoherenceCoord parent;
    public int bin;

    public CoherenceCoord(int x, int y, CoherenceCoord source) {
        this.x = x;
        this.y = y;
        this.parent = source;
    }

    public List<CoherenceCoord> getConnectedCoords(CoherenceCoord srcCoord) {
        List<CoherenceCoord> coords = new ArrayList<>();

        // 4-connected
        coords.add(new CoherenceCoord(srcCoord.x - 1, y, srcCoord));
        coords.add(new CoherenceCoord(srcCoord.x, y - 1, srcCoord));
        coords.add(new CoherenceCoord(srcCoord.x + 1, y, srcCoord));
        coords.add(new CoherenceCoord(srcCoord.x, y + 1, srcCoord));

        return coords;
    }
}
