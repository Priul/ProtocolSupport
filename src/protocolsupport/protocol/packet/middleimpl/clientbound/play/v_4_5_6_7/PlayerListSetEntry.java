package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7;

import java.util.Map.Entry;
import java.util.UUID;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddlePlayerListSetEntry;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.storage.netcache.PlayerListCache.PlayerListEntry;
import protocolsupport.protocol.typeremapper.legacy.chat.LegacyChat;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class PlayerListSetEntry extends MiddlePlayerListSetEntry {

	public PlayerListSetEntry(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public RecyclableCollection<? extends IPacketData> toData() {
		String locale = cache.getAttributesCache().getLocale();
		RecyclableArrayList<ClientBoundPacketData> packets = RecyclableArrayList.create();
		switch (action) {
			case ADD: {
				for (Entry<UUID, PlayerListOldNewEntry> entry : infos.entrySet()) {
					PlayerListEntry oldEntry = entry.getValue().getOldEntry();
					if (oldEntry != null) {
						packets.add(createRemove(version, oldEntry.getCurrentName(locale)));
					}
					PlayerListEntry currentEntry = entry.getValue().getNewEntry();
					packets.add(createAddOrUpdate(version, currentEntry.getCurrentName(locale), (short) currentEntry.getPing()));
				}
				break;
			}
			case PING: {
				for (Entry<UUID, PlayerListOldNewEntry> entry : infos.entrySet()) {
					PlayerListEntry currentEntry = entry.getValue().getNewEntry();
					packets.add(createAddOrUpdate(version, currentEntry.getCurrentName(locale), (short) currentEntry.getPing()));
				}
				break;
			}
			case DISPLAY_NAME: {
				for (Entry<UUID, PlayerListOldNewEntry> entry : infos.entrySet()) {
					packets.add(createRemove(version, entry.getValue().getOldEntry().getCurrentName(locale)));
					PlayerListEntry currentEntry = entry.getValue().getNewEntry();
					packets.add(createAddOrUpdate(version, currentEntry.getCurrentName(locale), (short) currentEntry.getPing()));
				}
				break;
			}
			case REMOVE: {
				for (Entry<UUID, PlayerListOldNewEntry> entry : infos.entrySet()) {
					packets.add(createRemove(version, entry.getValue().getOldEntry().getCurrentName(locale)));
				}
				break;
			}
			default: {
				break;
			}
		}
		return packets;
	}

	protected static ClientBoundPacketData createAddOrUpdate(ProtocolVersion version, String name, short ping) {
		return create(version, name, true, ping);
	}

	protected static ClientBoundPacketData createRemove(ProtocolVersion version, String name) {
		return create(version, name, false, (short) 0);
	}

	protected static ClientBoundPacketData create(ProtocolVersion version, String name, boolean addOrUpdate, short ping) {
		ClientBoundPacketData serializer = ClientBoundPacketData.create(PacketType.CLIENTBOUND_PLAY_PLAYER_INFO);
		StringSerializer.writeString(serializer, version, LegacyChat.clampLegacyText(name, 16));
		serializer.writeBoolean(addOrUpdate);
		serializer.writeShort(ping);
		return serializer;
	}

}
