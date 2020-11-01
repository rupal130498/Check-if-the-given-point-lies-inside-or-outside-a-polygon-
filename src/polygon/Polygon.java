package polygon;
class Polygon
{ 
  
	static int PIN = 1000; 
	  
    static class Point_of_poly 
    { 
        double x; 
        double y; 
  
        public Point_of_poly(double x, double y) 
        { 
            this.x = x; 
            this.y = y; 
        } 
    }; 
  
 
    static boolean onBorder_poly(Point_of_poly  b, Point_of_poly p, Point_of_poly  c)  
    { 
        if (p.x <= Math.max(b.x, c.x) && 
            p.x >= Math.min(b.x, c.x) && 
            p.y <= Math.max(b.y, c.y) && 
            p.y >= Math.min(b.y, c.y)) 
        { 
            return true; 
        } 
        return false; 
    } 
  
    static boolean OnIntersect(Point_of_poly  b1, Point_of_poly p1,  
    		Point_of_poly  b2, Point_of_poly  p2)  
    { 
       
        int o1 = orientation(b1, p1, b2); 
        int o2 = orientation(b1, p1, p2); 
        int o3 = orientation(b2, p2, b1); 
        int o4 = orientation(b2, p2, p1); 
  
        
        if (o1 != o2 && o3 != o4) 
        { 
            return true; 
        } 
  
        
        if (o1 == 0 && onBorder_poly(b1,b2, p1))  
        { 
            return true; 
        } 
  
        
        if (o2 == 0 && onBorder_poly(b1, p2, p1))  
        { 
            return true; 
        } 
  
        if (o3 == 0 && onBorder_poly(b2, b1, p2)) 
        { 
            return true; 
        } 
  
        if (o4 == 0 && onBorder_poly(b2, p1, p2)) 
        { 
            return true; 
        } 
  
        return false;  
    } 
  
  
    static int orientation(Point_of_poly b, Point_of_poly  p, Point_of_poly  c)  
    { 
        double val = (p.y - b.y) * (c.x - p.x) 
                - (p.x - b.x) * (c.y - p.y); 
  
        if (val == 0)  
        { 
            return 0;  
        } 
        return (val > 0) ? 1 : 2; 
    } 
  
   
  
    static boolean isInside_poly(Point_of_poly polygon[], int no_of_ver, Point_of_poly p) 
    { 
        if (no_of_ver < 3)  
        { 
            return false; 
        } 
   
        Point_of_poly  extreme = new Point_of_poly (PIN, p.y); 
  
     
        int count = 0, i = 0; 
        do 
        { 
            int next = (i + 1) % no_of_ver; 
   
            if (OnIntersect(polygon[i], polygon[next], p, extreme))  
            { 
                 
                if (orientation(polygon[i], p, polygon[next]) == 0) 
                { 
                    return onBorder_poly(polygon[i], p, 
                                     polygon[next]); 
                } 
  
                count++; 
            } 
            i = next; 
        } while (i != 0); 
  
        return (count % 2 == 1); 
    } 
  
    public static void main(String[] args)  
    { 
    	Point_of_poly  polygon1[] = {new Point_of_poly (-3,2), 
                            new Point_of_poly (-2,-0.8),  
                            new Point_of_poly (0,1.2),  
                            new Point_of_poly (2.2,0),
                            new Point_of_poly (2,4.5)}; 
        int n = polygon1.length; 
        Point_of_poly  p = new Point_of_poly (0, 0); 
        if (isInside_poly(polygon1, n, p)) 
        { 
            System.out.println("Yes"); 
        }  
        else 
        { 
            System.out.println("No"); 
        } 
        
        Point_of_poly  polygon2[] = {new Point_of_poly (1,0), 
                new Point_of_poly (8,3),  
                new Point_of_poly (8,8),  
                new Point_of_poly (1,5),
                }; 
        
         n = polygon2.length; 
         p = new Point_of_poly (3, 5); 
        if (isInside_poly(polygon2, n, p)) 
        { 
           System.out.println("Yes"); 
        }  
        else 
        { 
           System.out.println("No"); 
        } 
        
    } 
} 
  

