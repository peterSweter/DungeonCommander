package com.sweter.game.interfaces;

import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.Path;

/**
 * Created by pawel on 31.05.16.
 */
public interface PathFinder {
    Path findPath(Character toMove, Vector3 s, Vector3 t);
}
