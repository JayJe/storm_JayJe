import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;

/**
 * Created by honey on 17. 3. 2.
 */
public class SentenceSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String [] sentences = {
            "my dog has fleas",
            "i like cold beverages",
            "the dog ate my homework",
            "dont't have a cow man",
            "i dont't think i like fleas"
    };
    private int index = 0;

    public void declareOutputFields(OutputFieldsDeclarer declarer){
        declarer.declare(new Fields("sentence"));
    }
    public void open(Map config, TopologyContext context,
                     SpoutOutputCollector collector){
        this.collector = collector;
    }


    public void nextTuple(){
        this.collector.emit(new Values(sentences[index]));
        index++;
        if(index >= sentences.length){
            index = 0;
        }
        Utils.sleep(1000);
    }
}
