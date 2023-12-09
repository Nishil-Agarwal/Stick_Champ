package com.example.ap_proj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StickTest {
    @Test
    public void testlength() throws InterruptedException {
        Stick stick= new Stick(10,10);
        if(stick.getlength()<0){
            assertEquals(true,false);
        }
        else{
            assertEquals(true,true);
        }
    }

    @Test
    public void testfall1() throws InterruptedException {
        Stick stick= new Stick(20,10);
        assertEquals(stick.checkfall(10,29,4),1);
    }

    @Test
    public void testfall2() throws InterruptedException {
        Stick stick= new Stick(20,10);
        assertEquals(stick.checkfall(10,20,15),1);
    }

    @Test
    public void testfall3() throws InterruptedException {
        Stick stick= new Stick(20,10);
        assertEquals(stick.checkfall(10,29,10),1);
    }

}
