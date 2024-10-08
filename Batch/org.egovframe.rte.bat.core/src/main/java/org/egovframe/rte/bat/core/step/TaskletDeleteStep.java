package org.egovframe.rte.bat.core.step;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.File;

public class TaskletDeleteStep implements Tasklet, InitializingBean {

	// 실행시 사용하는 Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskletDeleteStep.class);
		
	private Resource directory;
	private Resource file;

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOGGER.debug("TaskletDeleteStep execute START ===");

		File fileInfo = directory.getFile();
		if ( fileInfo.isDirectory() == true ) { // Directory
			File[] files = fileInfo.listFiles();
			Boolean isEmpty = ArrayUtils.isEmpty(files);
			if (!isEmpty && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					boolean deleted = files[i].delete();
					if (!deleted) {
						throw new UnexpectedJobExecutionException("Could not delete file " + files[i].getPath());
					} else {
						System.out.println(files[i].getPath() + " is deleted!");
					}
				}
			} else {
				throw new NullPointerException("File does not exist.");
			}
		} else { // File
			boolean deleted = fileInfo.delete();
			if (!deleted) {
				throw new UnexpectedJobExecutionException("Could not delete file " + fileInfo.getPath());
			} else {
				System.out.println(fileInfo.getPath() + " is deleted!");
			}
		}

		return RepeatStatus.FINISHED;
	}

	public Resource getDirectory() {
		LOGGER.debug("getDirectory = " + directory);
		return directory;
	}

	public void setDirectory(Resource directory) {
		LOGGER.debug("setDirectory = " + directory);
		this.directory = directory;
	}

}
