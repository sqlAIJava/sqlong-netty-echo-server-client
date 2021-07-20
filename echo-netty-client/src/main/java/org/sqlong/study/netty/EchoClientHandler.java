package org.sqlong.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  // 不必要 属性 用于多 客户端 调试
  private final int clientId;

  public EchoClientHandler(int clientId) {
    this.clientId = clientId;
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    // 被通知Channel活跃 发送  一条消息
    ctx.writeAndFlush(Unpooled.copiedBuffer("【" + clientId + "】 hello echo server ! Are you ok.", CharsetUtil.UTF_8));
  }
  @Override
  public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
    // 纪录 接收 消息
    System.out.println("Client 【" + clientId + "】 received: " + in.toString(CharsetUtil.UTF_8));
  }
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 错误处理
    cause.printStackTrace();
    ctx.close();
  }
}
