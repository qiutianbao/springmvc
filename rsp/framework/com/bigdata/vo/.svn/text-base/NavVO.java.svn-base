package com.bigdata.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: folger.qin
 * Date: 12-5-14
 * Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
public class NavVO {
    private String id;                                                                           //tabid 标签的标识要唯一
    private String data;                                                                       //标签的名称
    private Integer openWinType;                                                   // 是否打开新窗口页,赋值方式:有 不赋值:本层      win:赋值类型        dialog:用js插件的lhgDialog
    private String width;                                                                    //只有打开新窗口要设置宽
    private String height;                                                                  //只有打开新窗口要设置高
    private List<NavVO> children;                                             //子树子菜单
    private Attr attr=new Attr();
    
    public class Attr{
    	private String id;//参数,表示
    	private String url;
    	private String text;
		public String getUrl() {
			return url;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Integer getOpenWinType() {
        return openWinType;
    }

    public void setOpenWinType(Integer openWinType) {
        this.openWinType = openWinType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
 

    public List<NavVO> getChildren() {
		return children;
	}

	public void setChildren(List<NavVO> children) {
		this.children = children;
	}

	public Attr getAttr() {
		return attr;
	}

	public void setAttr(Attr attr) {
		this.attr = attr;
	}

	public NavVO() {
    }

    public NavVO(String id, String data, String url,String parameter) {
        this.id = id;
        this.data = data;
        this.attr.setUrl(url);
        this.attr.setText(data);
        this.attr.setId(parameter);
    }

    public NavVO(String id, String data, String url, int openWinType, String width, String height) {
        this.id = id;
        this.data = data;
        this.attr.setUrl(url);
        this.attr.setText(data);
        this.openWinType = openWinType;
        this.width = width;
        this.height = height;
    }
}
