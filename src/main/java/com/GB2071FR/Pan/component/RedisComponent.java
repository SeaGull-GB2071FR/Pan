package com.GB2071FR.Pan.component;

import com.GB2071FR.Pan.entity.constants.Constants;
import com.GB2071FR.Pan.entity.dto.DownloadFileDto;
import com.GB2071FR.Pan.entity.dto.SysSettingsDto;
import com.GB2071FR.Pan.entity.dto.UserSpaceDto;
import com.GB2071FR.Pan.entity.po.FileInfo;
import com.GB2071FR.Pan.entity.po.UserInfo;
import com.GB2071FR.Pan.entity.query.FileInfoQuery;
import com.GB2071FR.Pan.entity.query.UserInfoQuery;
import com.GB2071FR.Pan.mapper.UserInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component("redisComponent")
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;

//    @Resource
//    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;

//    @Resource
//    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

    public SysSettingsDto getSysSettingDto(){
        SysSettingsDto sysSettingsDto = (SysSettingsDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        if(null == sysSettingsDto){
            sysSettingsDto = new SysSettingsDto();
            redisUtils.set(Constants.REDIS_KEY_SYS_SETTING,sysSettingsDto);
        }
        return sysSettingsDto;
    }
    public void saveSysSettingsDto(SysSettingsDto sysSettingsDto){
        redisUtils.set(Constants.REDIS_KEY_SYS_SETTING,sysSettingsDto);
    }

    public void saveUserSpaceUse(String userId, UserSpaceDto userSpaceDto){
        redisUtils.setex(Constants.REDIS_KEY_USER_SPACE_USE+userId,userSpaceDto,Constants.REDIS_KEY_EXPIRES_DAY);
    }
//    public UserSpaceDto resetUserSpaceUse(String userId){
//        UserSpaceDto spaceDto = new UserSpaceDto();
//        Long useSpace = this.fileInfoMapper.selectUseSpace(userId);
//        spaceDto.setUserSpace(useSpace);
//
//        UserInfo userInfo = this.userInfoMapper.selectByUserId(userId);
//        spaceDto.setTotalSpace(userInfo.getTotalSpace());
//        redisUtils.setex(Constants.REDIS_KEY_USER_SPACE_USE + userId, spaceDto, Constants.REDIS_KEY_EXPIRES_DAY);
//        return spaceDto;
//    }

//    public UserSpaceDto getUserSpaceUse(String userId){
//        UserSpaceDto spaceDto = (UserSpaceDto) redisUtils.get(Constants.REDIS_KEY_USER_SPACE_USE + userId);
//        if(spaceDto == null){
//            spaceDto = new UserSpaceDto();
//            Long useSpace = fileInfoMapper.selectUseSpace(userId);
//            spaceDto.setUserSpace(useSpace);
//            spaceDto.setTotalSpace(getSysSettingDto().getUserInitUseSpace()*Constants.MB);
//            saveUserSpaceUse(userId,spaceDto);
//        }
//        return spaceDto;
//    }
    public void saveFileTempSize(String userId,String fileId,Long fileSize){
        Long currentSize = getFileTempSize(userId,fileId);
        redisUtils.setex(Constants.REDIS_KEY_USER_FILE_TEMP_SIZE + userId +fileId,currentSize+fileSize,Constants.REDIS_KEY_EXPIRES_ONE_HOUR);
    }

    //获取临时文件大小
    public Long getFileTempSize(String userId,String fileId){
        Long currentSize = getFIleSizeFromRedis(Constants.REDIS_KEY_USER_FILE_TEMP_SIZE+userId+fileId);
        return currentSize;
    }

    private Long getFIleSizeFromRedis(String key){
        Object sizeObj = redisUtils.get(key);
        if(sizeObj == null){
            return 0L;
        }
        if (sizeObj instanceof Integer){
            return ((Integer)sizeObj).longValue();
        }else if (sizeObj instanceof Long){
            return (Long) sizeObj;
        }
        return 0L;
    }

    public void saveDownloadCode(String code, DownloadFileDto downloadFileDto){
        redisUtils.setex(Constants.REDIS_KEY_DOWNLOAD + code,downloadFileDto,Constants.REDIS_KEY_FIVE_MIN);
    }

    public DownloadFileDto getDownloadCode(String code){
        return (DownloadFileDto) redisUtils.get(Constants.REDIS_KEY_DOWNLOAD + code);
    }

}
