/*
 * Copyright (c) 2021-present, NoBugLady Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.github.nobuglady.network.fw.queue.ready;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import io.github.nobuglady.network.fw.executor.INodeExecutor;
import io.github.nobuglady.network.fw.queue.ReadyQueueConsumerThread;

/**
 * 
 * @author NoBugLady
 *
 */
public class ReadyQueueManager implements IReadyQueue {

	private BlockingQueue<ReadyNodeResult> nodeCompleteQueue = new LinkedBlockingQueue<ReadyNodeResult>();

	private ReadyQueueConsumerThread readyQueueConsumerThread;

	/**
	 * takeCompleteNode
	 * 
	 * @return ReadyNodeResult
	 * @throws InterruptedException InterruptedException
	 */
	public ReadyNodeResult takeReadyNode() throws InterruptedException {
		return nodeCompleteQueue.take();
	}

	/**
	 * putReadyNode
	 * 
	 * @param flowId    flowId
	 * @param historyId historyId
	 * @param nodeId    nodeId
	 */
	public void putReadyNode(String flowId, String historyId, String nodeId) {
		try {
			nodeCompleteQueue.put(new ReadyNodeResult(flowId, historyId, nodeId));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * startConsumerThread
	 * 
	 * @param nodeExecutor nodeExecutor
	 */
	public void startConsumerThread(INodeExecutor nodeExecutor) {
		readyQueueConsumerThread = new ReadyQueueConsumerThread(this, nodeExecutor);
		readyQueueConsumerThread.start();
	}

	/**
	 * shutdown
	 */
	public void shutdown() {
		if (readyQueueConsumerThread != null) {
			readyQueueConsumerThread.shutdown();
		}
	}

}
