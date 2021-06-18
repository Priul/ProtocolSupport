package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_17;

import protocolsupport.protocol.codec.StringCodec;
import protocolsupport.protocol.codec.chat.ChatCodec;
import protocolsupport.protocol.packet.ClientBoundPacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleResourcePack;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.storage.netcache.ClientCache;

public class ResourcePack extends MiddleResourcePack {

	public ResourcePack(MiddlePacketInit init) {
		super(init);
	}

	protected final ClientCache clientCache = cache.getClientCache();

	@Override
	protected void write() {
		ClientBoundPacketData resourcepackPacket = ClientBoundPacketData.create(ClientBoundPacketType.PLAY_RESOURCE_PACK);
		StringCodec.writeVarIntUTF8String(resourcepackPacket, url);
		StringCodec.writeVarIntUTF8String(resourcepackPacket, hash);
		resourcepackPacket.writeBoolean(forced);
		ChatCodec.serialize(version, clientCache.getLocale(), forcedText);
		codec.writeClientbound(resourcepackPacket);
	}

}
