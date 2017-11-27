package cs5700.hw4.sudoku;

import java.util.LinkedHashSet;

public class Block extends Group {

    private int blockIndex;

    public Block(LinkedHashSet block, int blockIndex) {
        super(block);
        this.blockIndex = blockIndex;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    @Override
    String groupFormat(int i) {
        if (++i % Math.sqrt(getSize()) == 0) {
            return " \n";
        } else {
            return " ";
        }
    }
}
