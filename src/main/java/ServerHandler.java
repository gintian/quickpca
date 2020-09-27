
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String value = (String)msg;
        HashMap BankFind = GlobalVariable.get();
        List list = null;
        try {
            list = Find.Find(value, BankFind);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
            ctx.writeAndFlush(array.toJSONString());
            ctx.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }
}