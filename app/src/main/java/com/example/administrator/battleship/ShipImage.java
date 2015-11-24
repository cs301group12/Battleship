package com.example.administrator.battleship;

import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by aljawad18 on 11/24/2015.
 */
public class ShipImage implements Serializable {

    float shipX;
    float shipY;
    float maxWidth;
    float maxHeight;
    boolean onGrid;
    boolean hit;
    Paint paint;
    boolean isVertical;
    boolean overlap;

    public ShipImage()
    {
        shipX = 0;
        shipY = 0;
        maxWidth = 0;
        maxHeight = 0;
        onGrid = false;
        hit = false;
        isVertical = false;
        overlap = false;
    }

    public ShipImage(float originX,float originY,float maximumWidth,float maximumHeight,boolean isOnGrid, boolean isHit,
                     boolean isVertical,boolean isOverlapping)
    {
        this.shipX = originX;
        this.shipY = originY;
        this.maxWidth = maximumWidth;
        this.maxHeight = maximumHeight;
        this.onGrid = isOnGrid;
        this.hit = isHit;
        this.isVertical = isVertical;
        this.overlap = isOverlapping;
    }

    public void setPosition(float x,float y)
    {
        this.shipX = x;
        this.shipY = y;
    }

    public void setOverlap(boolean isOverlapping)
    {
        this.overlap = isOverlapping;
    }

    public void setOnGrid(boolean isOnGrid)
    {
        this.onGrid = isOnGrid;
    }

    public void setIsHit(boolean isHit)
    {
        this.hit = isHit;
    }

    public void setIsVertical(boolean vertical)
    {
        this.isVertical = vertical;
    }

    public void isHit()
    {
        hit = true;
    }

    public void vertical()
    {
        isVertical = true;
    }

    public void overlapping()
    {
        overlap = true;
    }

    public void onGrid()
    {
        onGrid = true;
    }

}
