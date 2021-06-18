package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15;

import protocolsupport.api.chat.ChatAPI.MessagePosition;
import protocolsupport.protocol.codec.MiscDataCodec;
import protocolsupport.protocol.codec.StringCodec;
import protocolsupport.protocol.codec.chat.ChatCodec;
import protocolsupport.protocol.packet.ClientBoundPacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleChat;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.storage.netcache.ClientCache;

public class Chat extends MiddleChat {

	protected final ClientCache clientCache = cache.getClientCache();

	public Chat(MiddlePacketInit init) {
		super(init);
	}

	@Override
	protected void write() {
		codec.writeClientbound(create(position, ChatCodec.serialize(version, clientCache.getLocale(), message)));
	}

	public static ClientBoundPacketData create(MessagePosition position, String messageJson) {
		ClientBoundPacketData chat = ClientBoundPacketData.create(ClientBoundPacketType.PLAY_CHAT);
		StringCodec.writeVarIntUTF8String(chat, messageJson);
		MiscDataCodec.writeByteEnum(chat, position);
		return chat;
	}

}
