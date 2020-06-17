package ua.realalpha.ragot.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PageController {

    private List<RInventoryData> list;
    private final RInventory rInventory;
    private final Map<Integer, List<RInventoryData>> map;
    private int[] board;
    private int page;

    public PageController(RInventory rInventory) {
        this.rInventory = rInventory;
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.page = 0;
        this.board = new int[]{};
    }

    public void setBoard(int slotfrom, int lenth, int width){
        this.board = this.rInventory.getBoard(slotfrom, lenth, width);
    }
    public void setBoard(int... ints){
        this.board = ints;
    }

    public void setItemStacks(List<RInventoryData> list) {
        this.list = list;
    }


    public void nextPage(){
        if (!isLast()) {
            this.page++;
            this.setPage(this.page);
        }
    }

    public void previousPage(){
        if(!isFirst()) {
            this.page--;
            this.setPage(this.page);
        }
    }

    private void setPage(int page){
        for (int i : this.board) {
            this.rInventory.getMapShare().remove(i);
            this.rInventory.getInventory().clear(i);
        }
        List<RInventoryData> rInventoryData = this.map.get(page);
        for (int i = 0; i < rInventoryData.size(); i++) {
            RInventoryData data = rInventoryData.get(i);
            this.rInventory.setItem(this.board[i], data.getItemStack(), data.getConsumer());
        }
    }

    protected void setUp() throws NumberFormatException{
        if (this.board.length == 0)  throw new NumberFormatException("The board is empty");
        this.map.clear();
        this.page = 0;
        int page = 0;
        int size = 0;
        for (RInventoryData rInventoryData : this.list) {
            if (size == board.length){
                page++;
                size=0;
            }
            if (!this.map.containsKey(page)) this.map.put(page, new ArrayList<>());
            this.map.get(page).add(rInventoryData);
            size++;
        }
        if(this.map.size() != 0) this.setPage(0);
    }

    public final int getPage() {
        return page;
    }

    public final int getMaxPage(){
        return this.map.size();
    }

    public final boolean isFirst(){ return this.page == 0; }

    public final boolean isLast(){ return (getPage()+1) == (getMaxPage() == 0? 1 : getMaxPage()); }

}
