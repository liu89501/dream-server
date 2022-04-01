package com.dream.server.param;

import com.dream.server.utils.MyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterSaver;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPageData<T extends ParameterSaver> implements ParameterSaver
{
    private int totalPage;
    private int totalNum;
    private List<T> data;

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(totalPage);
        packet.writeInt(totalNum);

        int dataSize = MyUtils.isValidCollection(data) ? data.size() : 0;
        packet.writeInt(dataSize);

        if (dataSize > 0)
        {
            for (ParameterSaver d : data)
            {
                d.save(packet);
            }
        }
    }
}
