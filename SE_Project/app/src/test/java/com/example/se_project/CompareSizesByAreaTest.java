package com.example.se_project;

import org.junit.Assert;
import org.junit.Test;
import android.util.Size;
import static org.junit.Assert.*;

public class CompareSizesByAreaTest {

    @Test
    public void compare1() {
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        Size size1 = new Size(5,5);
        Size size2 = new Size(6,6);
        Assert.assertTrue(compareSizesByArea.compare(size1,size2) < 0);
    }

    @Test
    public void compare2() {
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        Size size1 = new Size(5,5);
        Size size2 = new Size(6,6);
        Assert.assertTrue(compareSizesByArea.compare(size2,size1) > 0);
    }
}