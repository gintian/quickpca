import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class Server {

    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            System.out.println("监听端口: 3001 ... ok");
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024*32)
                    .option(ChannelOption.SO_RCVBUF, 1024*32)
                    .childHandler(new ChildChannelHandler());
            ChannelFuture f=b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("监听端口: 3001 ... err");
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            ChannelPipeline p = arg0.pipeline();
            p.addLast(new LineBasedFrameDecoder(1024*1024)); //换行符解包器
            p.addLast(new StringEncoder()); //发送编码器
            p.addLast(new StringDecoder());//接收编码器
            p.addLast(new ServerHandler());

        }
    }


}