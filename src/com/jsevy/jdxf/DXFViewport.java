/*
 * JDXF Library
 * 
 *   Copyright (C) 2018, Jonathan Sevy <jsevy@jsevy.com>
 *   
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *   
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *   
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 * 
 */

package com.jsevy.jdxf;


/**
 * Class representing a viewport for use in VPORTs table.
 * @author jsevy
 *
 */
public class DXFViewport extends DXFTableRecord
{
    private String name;
    private double viewportScale;
    private RealPoint viewportCenter;
    
    
    /**
     * Create a VPORT table record object with specified name.
     * 
     * @param name              name of table record
     * @param viewportScale     scale of viewport - number of drawing units the viewport spans
     */
    public DXFViewport(String name, double viewportScale)
    {
        this(name, viewportScale/2, viewportScale/2, viewportScale);
    }
    
    
    /**
     * Create a VPORT table record object with specified name.
     * 
     * @param name              name of table record
     * @param centerX           Location of x coordinate of the viewing center of the viewport
     * @param centerY           Location of y coordinate of the viewing center of the viewport
     * @param viewportScale     scale of viewport - number of drawing units the viewport spans
     */
    public DXFViewport(String name, double centerX, double centerY,
                                    double viewportScale)
    {
        this.name = name;
        this.viewportCenter = new RealPoint(centerX, centerY, 0);
        this.viewportScale = viewportScale;
        
    }
    
    
    public void setScale(double viewportScale)
    {
        this.viewportScale = viewportScale;
    }
    
    
    public void setCenter(double centerX, double centerY)
    {
        this.viewportCenter = new RealPoint(centerX, centerY, 0);
    }
    
    
    /**
     * Implementation of DXFObject interface method; creates DXF text representing the text style.
     */
    public String toDXFString()
    {
        String returnString = new String();
        
        returnString += "0\nVPORT\n";
        
        // print out handle and superclass marker(s)
        returnString += super.toDXFString();
        
        // print out subclass marker
        returnString += "100\nAcDbViewportTableRecord\n";
        
        returnString += "2\n" + name + "\n";
        
        // these set the viewport bounds; code 40 sets the scale
        returnString += "10\n0\n";
        returnString += "20\n0\n";
        
        returnString += "11\n1\n";
        returnString += "21\n1\n";
        
        returnString += "12\n" + viewportCenter.x + "\n";
        returnString += "22\n" + viewportCenter.y + "\n";
        
        returnString += "40\n" + viewportScale + "\n";
        
        // no flags set
        returnString += "70\n0\n";
        
        return returnString;
    }
    
}