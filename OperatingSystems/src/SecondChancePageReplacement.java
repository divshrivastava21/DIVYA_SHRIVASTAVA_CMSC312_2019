import java.util.Arrays;

public class SecondChancePageReplacement extends Process {

    public static void main(String reference_string)
    {
        int frames = 3;

        printHitsAndFaults(reference_string, frames);

    }

    //If page found, updates the second chance bit to true
    static boolean findAndUpdate(int x, int arr[], boolean secondChance[],int frames)
    {

        for(int i = 0; i < frames; i++)
        {
            if(arr[i] == x)
            {
                secondChance[i] = true;

                return true;
            }
        }
        return false;
    }

    //Updates the page in memory and returns the pointer
    static int replaceAndUpdate(int x, int arr[], boolean secondChance[], int frames, int pointer)
    {
        while(true)
        {
            if(!secondChance[pointer])
            {
                arr[pointer] = x;
                return (pointer+1)%frames;
            }
            secondChance[pointer] = false;

            pointer = (pointer+1)%frames;
        }
    }

    static void printHitsAndFaults(String reference_string, int frames)
    {
        int pointer = 0;
        int length;
        int x;
        int pageFaults = 0;
        int arr[] = new int[frames];
        Arrays.fill(arr,-1);
        boolean secondChance[] = new boolean[frames];
        String pageNumbers[] = reference_string.split(" ");
        length = pageNumbers.length;

        for(int i = 0; i < length; i++)
        {
            try {
                x = Integer.parseInt(pageNumbers[i]);
                if(!findAndUpdate(x,arr,secondChance,frames))
                {
                    pointer = replaceAndUpdate(x,arr, secondChance,frames,pointer);

                    pageFaults++;
                }
            }
            catch(NumberFormatException e) { }
        }
        System.out.println("Total page faults were "+ pageFaults);
    }
}