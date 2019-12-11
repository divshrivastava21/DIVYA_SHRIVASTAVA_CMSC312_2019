
public class AddressTranslator extends Process {

    public static void main(){

        int virtualAddress;
        int pageNum;
        int offset;
        int frameNum;
//        int value;
        int physicalAddress;
        int notInTLB = 0;
        int pageFault = 0;
        String referenceNumber = " ";


        try{
            TLB tlb = new TLB();
            PageTable pt = new PageTable();
            PhysicalMemory pm = new PhysicalMemory();
            BackingStore bs = new BackingStore();


            virtualAddress = ((int)getRandomInt()) % 65536;
            //virtualAddress = virtualAddress % 65536;
            offset = virtualAddress % 256;
            pageNum = virtualAddress / 256;
            setReferenceNumber(pageNum);


            frameNum = 0;
            frameNum = tlb.get(pageNum);
            if(frameNum == -1){
                notInTLB++;
                setTLBCounter(notInTLB);
                frameNum = pt.get(pageNum);
                if(frameNum == -1){
                    pageFault++;
                    setPageFaultsCounter(pageFault);
                    Frame f = new Frame(bs.getData(pageNum));
                    frameNum = pm.addFrame(f);
                    pt.add(pageNum, frameNum);
                    tlb.put(pageNum, frameNum);
                }
            }
            physicalAddress = frameNum * 256 + offset;

            System.out.println(String.format("Virtual address: %s Physical address: %s", virtualAddress,
                    physicalAddress));

            System.out.println(String.format("TLB miss: %s, Page Fault: %s", notInTLB, pageFault));

        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0); }
    }

    private static double getRandomInt() {
        double randomInteger = (int) (Math.random() * (((double) 1000000 - 1.0) + 1)) + 1.0;
        return randomInteger;
    }
}