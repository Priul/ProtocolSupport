package protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.codec.UUIDCodec;
import protocolsupport.protocol.packet.middle.base.serverbound.play.MiddleSpectate;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV10;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV11;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV12r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV12r2;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV13;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV14r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV14r2;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV15;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV16r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV16r2;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV17r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV17r2;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV8;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV9r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV9r2;

public class Spectate extends MiddleSpectate implements
IServerboundMiddlePacketV8,
IServerboundMiddlePacketV9r1,
IServerboundMiddlePacketV9r2,
IServerboundMiddlePacketV10,
IServerboundMiddlePacketV11,
IServerboundMiddlePacketV12r1,
IServerboundMiddlePacketV12r2,
IServerboundMiddlePacketV13,
IServerboundMiddlePacketV14r1,
IServerboundMiddlePacketV14r2,
IServerboundMiddlePacketV15,
IServerboundMiddlePacketV16r1,
IServerboundMiddlePacketV16r2,
IServerboundMiddlePacketV17r1,
IServerboundMiddlePacketV17r2 {

	public Spectate(IMiddlePacketInit init) {
		super(init);
	}

	@Override
	protected void read(ByteBuf clientdata) {
		entityUUID = UUIDCodec.readUUID2L(clientdata);
	}

}