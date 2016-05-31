package com.sweter.game.interfaces;

import com.sweter.game.entities.Path;

/**
 * Created by pawel on 31.05.16.
 */
public interface PathFinder {
    Path findPath(Character toMove, int sx, int sy, int tx, int ty);
}
