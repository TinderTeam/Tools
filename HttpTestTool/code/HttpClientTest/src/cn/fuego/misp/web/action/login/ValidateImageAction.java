package cn.fuego.misp.web.action.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import cn.fuego.misp.web.action.basic.MISPAction;

import com.opensymphony.xwork2.ActionContext;

public class ValidateImageAction extends MISPAction
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ByteArrayInputStream codeStream; 
	/*
	 *生产验证码
	 */
	  /**  
     * 生成随机颜色  
     * @param fc    前景色  
     * @param bc    背景色  
     * @return  Color对象，此Color对象是RGB形式的。  
     */     
    private Color getRandColor(int fc,int bc)
    {    
        Random random = new Random();    
        if(fc>255) fc=255;    
        if(bc>255) bc=255;    
        int r=fc+random.nextInt(bc-fc);    
        int g=fc+random.nextInt(bc-fc);    
        int b=fc+random.nextInt(bc-fc);    
        return new Color(r,g,b);    
   }   
      
    public String createImage() 
    {  
//      在内存中创建图象    
        int width=65, height=20;    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    
//       获取图形上下文    
        Graphics g = image.getGraphics();    
//      生成随机类    
        Random random = new Random();    
//       设定背景色    
        g.setColor(getRandColor(200,250));    
        g.fillRect(0, 0, width, height);    
//      设定字体    
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));    
//       随机产生155条干扰线，使图象中的认证码不易被其它程序探测到    
        g.setColor(getRandColor(160,200));    
        for (int i=0;i<155;i++)    
        {    
         int x = random.nextInt(width);    
         int y = random.nextInt(height);    
                int xl = random.nextInt(12);    
                int yl = random.nextInt(12);    
         g.drawLine(x,y,x+xl,y+yl);    
        }    
//       取随机产生的认证码(6位数字)    
        String sRand="";    
        for (int i=0;i<4;i++){    
            String rand=String.valueOf(random.nextInt(10));    
            sRand+=rand;    
            // 将认证码显示到图象中    
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));    
//      调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成    
            g.drawString(rand,13*i+6,16);    
        }    
//       将认证码存入SESSION    
        ActionContext.getContext().getSession().put("rand",sRand);    
//       图象生效    
        g.dispose();    
        ByteArrayOutputStream output = new ByteArrayOutputStream();    
        ImageOutputStream imageOut = null;  
        try {  
            imageOut = ImageIO.createImageOutputStream(output);  
            ImageIO.write(image, "JPEG", imageOut);    
            imageOut.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }    
            
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());    
        this.setCodeStream(input);   
          
         
          
        return SUCCESS;  
    }  
      
    public String validateCode()
    {  
          
        String  strv = (String)ActionContext.getContext().getSession().get("rand");//获取图片的验证码  
        System.out.println("strv = " + strv);//在这里可以验证手动输入的验证与图片的验证是否相同  
        
        return  SUCCESS;
    }

	public ByteArrayInputStream getCodeStream()
	{
		return codeStream;
	}

	public void setCodeStream(ByteArrayInputStream codeStream)
	{
		this.codeStream = codeStream;
	}  
 
}
