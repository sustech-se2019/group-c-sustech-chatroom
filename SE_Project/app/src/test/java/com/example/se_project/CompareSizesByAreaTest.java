package com.example.se_project;

import org.junit.Assert;
import org.junit.Test;
import android.util.Size;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

public class CompareSizesByAreaTest {

    @Test
    public void compare1() {
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();

        Size size1 = Mockito.mock(Size.class);
        Size size2 = Mockito.mock(Size.class);

        Mockito.when(size1.getWidth()).thenReturn(5);
        Mockito.when(size1.getHeight()).thenReturn(5);
        Mockito.when(size2.getWidth()).thenReturn(6);
        Mockito.when(size2.getHeight()).thenReturn(6);


        Assert.assertTrue(compareSizesByArea.compare(size1,size2) < 0);
    }

    @Test
    public void compare2() {
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        Size size1 = Mockito.mock(Size.class);
        Size size2 = Mockito.mock(Size.class);

        Mockito.when(size1.getWidth()).thenReturn(5);
        Mockito.when(size1.getHeight()).thenReturn(5);
        Mockito.when(size2.getWidth()).thenReturn(6);
        Mockito.when(size2.getHeight()).thenReturn(6);
        Mockito.when(size2.getHeight()).thenReturn(6);
        Mockito.when(size2.getHeight()).thenReturn(6);
        Assert.assertTrue(compareSizesByArea.compare(size2,size1) > 0);
    }
}