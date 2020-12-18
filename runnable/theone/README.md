# kaist-sw-architecture

## sw-batch
- local run
  <pre>
  ./gradlew clean :sw-batch:bootRun  -Dspring.batch.job.names=SampleJob
  </pre>
  
## sw-subscribe
- local run
  <pre>
  ./gradlew clean :sw-subscribe:bootRun  -Dserver.port=8090
  </pre>
  
## table
- mysql ddl script
  <pre>
  CREATE TABLE `job_item` (
    `jobItemId` bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `type` varchar(255) NOT NULL COMMENT 'job type',
    `targetStartDate` date DEFAULT NULL COMMENT 'target start date',
    `targetEndDate` date DEFAULT NULL COMMENT 'target end date',
    `options` varchar(255) DEFAULT NULL COMMENT 'options string',
    `totalCount` double NOT NULL COMMENT 'total targets count',
    `successCount` double NOT NULL COMMENT 'success count',
    `failCount` double NOT NULL COMMENT 'fail count',
    `status` varchar(20) NOT NULL COMMENT 'status',
    PRIMARY KEY (`jobItemId`)
  );
  
  CREATE TABLE `job_item_detail` (
    `jobItemDetailId` bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `jobItemId` bigint NOT NULL COMMENT 'pk@scatch_job',
    `numberUnitId` bigint DEFAULT NULL COMMENT 'number type unit id',
    `stringUnitId` varchar(255) DEFAULT NULL COMMENT 'string type unit id',
    `status` varchar(20) NOT NULL COMMENT 'status',
    PRIMARY KEY (`jobItemDetailId`)
  );
  </pre>
