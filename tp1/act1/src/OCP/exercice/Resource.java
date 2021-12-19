package OCP.exercice;

public abstract class Resource {
    public abstract int findFreeSlot();
    public abstract void markSlotBusy(int resourceId);
    public abstract void markSlotFree(int resourceId);
}
