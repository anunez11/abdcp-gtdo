package pe.gtdo.util.adapters;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.json.JSONObject;

public class JsonAdapter extends XmlAdapter<Map<String,Object>, JSONObject> {


	@Override
	public JSONObject unmarshal(Map<String, Object> v) throws Exception {
		// TODO Auto-generated method stub

		return new JSONObject(v);
	}

	@Override
	public Map<String, Object> marshal(JSONObject v) throws Exception {
		// TODO Auto-generated method stub
		return  v!=null ? v.toMap() : new HashMap<String, Object>();
	}



}
