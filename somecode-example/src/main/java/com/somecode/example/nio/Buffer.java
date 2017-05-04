package com.somecode.example.nio;

import java.nio.IntBuffer;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Buffer {

    //创建指定长度的缓冲区
    public void init(int size){
        //方法1
        IntBuffer buffer = IntBuffer.allocate(size);
        //放入值
        buffer.put(13);
        buffer.put(21);
        buffer.put(35);
        System.out.println("容量："+buffer.capacity());
        System.out.println("填充："+buffer.limit());

        buffer.flip();//位置复原
        System.out.println("使用flip复位："+buffer);
        System.out.println("容量："+buffer.capacity());
        System.out.println("限制："+buffer.limit());

        System.out.println("获取下标元素："+buffer.get(1));

        for (int i=0;i<buffer.limit();i++){
            System.out.println(buffer.get()+"\t");//调用get方法使缓冲区位置向后递增一位
        }

        System.out.println("buffer对象遍历之后为："+buffer);


        //方法2，包裹
        int[] arr = new int[]{1,2,4};
        IntBuffer buffer1 = IntBuffer.wrap(arr);
        System.out.println(buffer1);

        IntBuffer buffer2 = IntBuffer.wrap(arr,0,2);//长度为数组长度
        System.out.println(buffer2);

    }
}
