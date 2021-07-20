package org.sqlong.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 通道引导事件处理器，不同生命周期对应不同方法
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //  每个入站事件 的 处理方法
    ByteBuf in = (ByteBuf) msg;
    System.out.println("Server received : " + in.toString(CharsetUtil.UTF_8));

    // 业务逻辑
    Thread.sleep(3000);
    in.writeCharSequence("[server had handled]", CharsetUtil.UTF_8);

    // 出现多次 写的 操作
    ctx.write(in);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    //  最后一个入站事件
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    //  异常处理
    cause.printStackTrace();
    ctx.close();
  }

}
