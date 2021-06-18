package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8;

import protocolsupport.protocol.codec.VarNumberCodec;
import protocolsupport.protocol.packet.ClientBoundPacketType;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8.AbstractSoundCollectEffect;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8.WorldCustomSound;

public class CollectEffect extends AbstractSoundCollectEffect {

	public CollectEffect(MiddlePacketInit init) {
		super(init);
	}

	@Override
	protected void writeCollectEffect() {
		ClientBoundPacketData collecteffect = ClientBoundPacketData.create(ClientBoundPacketType.PLAY_COLLECT_EFFECT);
		VarNumberCodec.writeVarInt(collecteffect, entityId);
		VarNumberCodec.writeVarInt(collecteffect, collectorId);
		codec.writeClientbound(collecteffect);
	}

	@Override
	protected void writeCollectSound(String sound, double x, double y, double z, float volume, float pitch) {
		codec.writeClientbound(WorldCustomSound.create(version, x, y, z, sound, volume, pitch));
	}

}
