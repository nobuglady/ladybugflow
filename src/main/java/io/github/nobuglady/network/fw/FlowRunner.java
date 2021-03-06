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
package io.github.nobuglady.network.fw;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import io.github.nobuglady.network.fw.annotation.Node;
import io.github.nobuglady.network.fw.logger.ConsoleLogger;
import io.github.nobuglady.network.fw.persistance.entity.HistoryNodeEntity;
import io.github.nobuglady.network.fw.starter.FlowStarter;

/**
 * 
 * @author NoBugLady
 *
 */
public class FlowRunner {

	private BlockingQueue<String> completeQueue = new LinkedBlockingQueue<>();

	private Object runnerObj = null;

	public FlowRunner() {
		runnerObj = this;
	}

	public FlowRunner(Object customRunnerObject) {
		runnerObj = customRunnerObject;
	}

	/**
	 * execute
	 * 
	 * @param flowId     flowId
	 * @param nodeId     nodeId
	 * @param historyId  historyId
	 * @param nodeEntity nodeEntity
	 * @return execute result
	 * @throws Exception Exception
	 */
	public String execute(String flowId, String nodeId, String historyId, HistoryNodeEntity nodeEntity)
			throws Exception {

		ConsoleLogger logger = ConsoleLogger.getInstance(flowId, historyId);

		String nodeName = nodeEntity.getNodeName();
		logger.info("execute node id:" + nodeId);
		logger.info("execute node name:" + nodeEntity.getNodeName());

		Method methods[] = runnerObj.getClass().getMethods();
		if (methods != null) {
			for (Method method : methods) {
				Node node = method.getAnnotation(Node.class);
				if (node != null) {
					if (node.id().equals(nodeId) || node.label().equals(nodeName)) {
						try {
							Object rtnObj = method.invoke(runnerObj);
							if (rtnObj != null) {
								return rtnObj.toString();
							} else {
								return null;
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
		}
		return null;

	}

	/**
	 * startFlow
	 * 
	 * @return start result
	 */
	public int startFlow() {

		try {
			Class.forName(FlowStarter.class.getName());
			FlowManager.startFlow(this, null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * startFlow
	 * 
	 * @param sync sync
	 * @return start result
	 */
	public int startFlow(boolean sync) {

		if (!sync) {
			return startFlow();
		} else {
			startFlow();
			try {
				completeQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	/**
	 * startFlowFromJson
	 * 
	 * @param jsonFileName jsonFileName
	 * @return start result
	 */
	public int startFlowFromJson(String jsonFileName) {

		try {
			Class.forName(FlowStarter.class.getName());
			FlowManager.startFlow(this, jsonFileName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * startFlowFromJson
	 * 
	 * @param jsonFileName jsonFileName
	 * @param sync         sync
	 * @return start result
	 */
	public int startFlowFromJson(String jsonFileName, boolean sync) {

		if (!sync) {
			return startFlowFromJson(jsonFileName);
		} else {
			startFlowFromJson(jsonFileName);
			try {
				completeQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	/**
	 * putComplete
	 * 
	 * @param result result
	 */
	public void putComplete(String result) {
		try {
			completeQueue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
