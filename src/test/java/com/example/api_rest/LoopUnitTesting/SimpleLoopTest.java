package com.example.api_rest.LoopUnitTesting;

public class SimpleLoopTest {
    private int[] numbers = {5, -77, 8, -11, 4, 1, -20, 6, 2, 10};

    public int findSum(int numItems)
    {
        int total = 0;
        if (numItems <= 10)
        {
            for (int count=0; count < numItems; count = count + 1)
            {
                if (numbers[count] > 0)
                {
                    total = total + numbers[count];
                }
            }   
        }
        return total;
    }
}
