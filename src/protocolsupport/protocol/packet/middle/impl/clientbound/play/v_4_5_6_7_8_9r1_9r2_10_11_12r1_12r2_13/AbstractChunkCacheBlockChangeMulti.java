package protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13;

import protocolsupport.protocol.packet.middle.MiddlePacketCancelException;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.AbstractLimitedHeightBlockChangeMulti;
import protocolsupport.protocol.storage.netcache.chunk.LimitedHeightCachedChunk;
import protocolsupport.protocol.storage.netcache.chunk.LimitedHeightChunkCache;
import protocolsupport.protocol.types.ChunkCoord;

public abstract class AbstractChunkCacheBlockChangeMulti extends AbstractLimitedHeightBlockChangeMulti {

	protected final LimitedHeightChunkCache chunkCache = cache.getChunkCache();

	protected AbstractChunkCacheBlockChangeMulti(IMiddlePacketInit init) {
		super(init);
	}

	@Override
	protected void handle() {
		super.handle();

		LimitedHeightCachedChunk cachedChunk = chunkCache.get(new ChunkCoord(chunkX, chunkZ));

		if (cachedChunk == null) {
			throw MiddlePacketCancelException.INSTANCE;
		}

		for (BlockChangeRecord record : records) {
			cachedChunk.setBlock(chunkSection, LimitedHeightCachedChunk.getBlockIndex(record.getRelX(), record.getRelY(), record.getRelZ()), (short) record.getBlockData());
		}
	}

}
