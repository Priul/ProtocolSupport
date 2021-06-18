package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2;

import protocolsupport.protocol.codec.StringCodec;
import protocolsupport.protocol.codec.VarNumberCodec;
import protocolsupport.protocol.codec.chat.ChatCodec;
import protocolsupport.protocol.packet.ClientBoundPacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleCombatDeath;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.storage.netcache.ClientCache;

public class CombatDeath extends MiddleCombatDeath {

	public CombatDeath(MiddlePacketInit init) {
		super(init);
	}

	protected final ClientCache clientCache = cache.getClientCache();

	@Override
	protected void write() {
		ClientBoundPacketData combatdeathPacket = ClientBoundPacketData.create(ClientBoundPacketType.PLAY_COMBAT_DEATH);
		VarNumberCodec.writeVarInt(combatdeathPacket, 2); //action (2 - death)
		VarNumberCodec.writeVarInt(combatdeathPacket, playerId);
		combatdeathPacket.writeInt(killerId);
		StringCodec.writeVarIntUTF8String(combatdeathPacket, ChatCodec.serialize(version, clientCache.getLocale(), message));
		codec.writeClientbound(combatdeathPacket);
	}

}
