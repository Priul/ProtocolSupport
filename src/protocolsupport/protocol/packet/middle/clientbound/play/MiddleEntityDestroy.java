package protocolsupport.protocol.packet.middle.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.codec.VarNumberCodec;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;
import protocolsupport.protocol.storage.netcache.NetworkEntityCache;

public abstract class MiddleEntityDestroy extends ClientBoundMiddlePacket {

	protected final NetworkEntityCache entityCache = cache.getEntityCache();

	protected MiddleEntityDestroy(MiddlePacketInit init) {
		super(init);
	}

	protected int entityId;

	@Override
	protected void decode(ByteBuf serverdata) {
		entityId = VarNumberCodec.readVarInt(serverdata);
	}

	@Override
	protected void handle() {
		entityCache.removeEntity(entityId);
	}

}
