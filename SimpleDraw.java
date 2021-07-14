import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SimpleDraw extends JFrame implements MouseListener, MouseMotionListener, ActionListener, ChangeListener {
	static DrawPanel panel;
	JSlider slider;
	JLabel label;
	JTextField moji_stamp;

	static JButton pen_b,eraser_b, color_b, spoit_b, circle_b, square_b, beimax_b, original_b, moji_korokoro_b, moji_stamp_b;

	static JRadioButton radio1, radio2, radio3;
	static JLabel color_l;
	static String moji_s;

	int lastx, lasty, newx, newy, click_x, click_y, release_x, release_y, color_num=1, word_num=0;
	static File original_pic;
	static boolean pen_bool = true;

	static boolean circle_bool = false;
	static boolean square_bool = false;
	static boolean beima_bool = false;
	static boolean moji_korokoro_bool=false;
	static boolean moji_stamp_bool=false;
	static boolean rainbow = false;
	static boolean spoit = false;
	static boolean original_bool = false;

	 ImageIcon icon_pen = new ImageIcon("./image/pen.png");
	 ImageIcon icon_pen_m = new ImageIcon("./image/pen_m.png");
	 ImageIcon icon_eraser = new ImageIcon("./image/eraser.png");
	 ImageIcon icon_eraser_m = new ImageIcon("./image/eraser_m.png");
	 ImageIcon icon_color = new ImageIcon("./image/color.png");
	 ImageIcon icon_spoit = new ImageIcon("./image/spoit.png");
	 ImageIcon icon_file_m = new ImageIcon("./image/file.png");
	 ImageIcon icon_open_m = new ImageIcon("./image/open.png");
	 ImageIcon icon_new_m = new ImageIcon("./image/new.png");
	 ImageIcon icon_save_m = new ImageIcon("./image/save.png");
	 ImageIcon icon_stamp_m = new ImageIcon("./image/stamp_m.png");
	 ImageIcon icon_pic = new ImageIcon("./image/pic.png");
	 ImageIcon icon_pic_m = new ImageIcon("./image/pic_m.png");
	 ImageIcon icon_circle = new ImageIcon("./image/circle.png");
	 ImageIcon icon_square = new ImageIcon("./image/square.png");
	 ImageIcon icon_circle_m = new ImageIcon("./image/circle_m.png");
	 ImageIcon icon_square_m = new ImageIcon("./image/square_m.png");

	 //ImageIcon icon_pen = new ImageIcon("Users/haruka/java/workspace/SimpleDraw/image/pen.png");


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		newx=e.getX();
	    newy=e.getY();

	    //mousePressed(e);
	    if(rainbow) {
	    	color_num = ((color_num+1)%256)+1;
			panel.setPenColor(Color.getHSBColor(color_num /256.0f,1.0f,1.0f));
			color_l.setBackground(panel.currentColor);
	    }

	    if(pen_bool) {
	    	panel.drawLine(lastx,lasty,newx,newy);
	    }
	    if(circle_bool) {
	    	panel.drawCircle(newx,newy);
	    }
	    if(square_bool) {
			panel.drawSquare(newx,newy);
		}
	    if(beima_bool) {
			panel.drawBeima(newx,newy);
		}
	    if(moji_korokoro_bool) {
	    	char[] one_word = new char[moji_s.length()];

	        for(int i = 0; i < moji_s.length(); i++){
	            	one_word[i] = moji_s.charAt(i);
	        }
	        panel.drawText(newx, newy, String.valueOf(one_word[word_num]));
	        word_num = (word_num+1)%moji_s.length();
	    }

	    lastx=newx;
		lasty=newy;
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		//click_x=e.getX();
		//click_y=e.getY();

		/*if(circle_bool) {
		panel.drawCircle(click_x,click_y);
		}*/
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		lastx=e.getX();
		lasty=e.getY();
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		release_x=e.getX();
		release_y=e.getY();

		if(circle_bool) {
			panel.drawCircle(release_x,release_y);
		}
		if(square_bool) {
			panel.drawSquare(release_x,release_y);
		}
		if(beima_bool) {
			panel.drawBeima(release_x,release_y);
		}
		if(original_bool) {
			panel.drawPictureStamp(release_x,release_y, original_pic);
		}
		if(moji_stamp_bool) {
	    	panel.drawText(release_x,release_y, moji_s);
	    }
		if(spoit) {
			panel.spoitColor(release_x,release_y);
			color_l.setBackground(panel.currentColor);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void addMenuItem
	(JMenu targetMenu, String itemName, String actionName, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(itemName);
		menuItem.setActionCommand(actionName);
		menuItem.addActionListener(listener);
		targetMenu.add(menuItem);
	}

	private void initMenu() {
		JMenuBar menubar=new JMenuBar();
		JMenu menuFile = new JMenu("File");
		menuFile.setIcon(icon_file_m);
		//this.addMenuItem(menuFile,"New","New",this);
		JMenuItem menuitem_new = new JMenuItem("New", icon_new_m);
		menuitem_new.setActionCommand("New");
		menuitem_new.addActionListener(this);
		menuFile.add(menuitem_new);
		//this.addMenuItem(menuFile,"Open...","Open",this);
		JMenuItem menuitem_open = new JMenuItem("Open...", icon_open_m);
		menuitem_open.setActionCommand("Open");
		menuitem_open.addActionListener(this);
		menuFile.add(menuitem_open);
		//this.addMenuItem(menuFile,"Save...","Save",this);
		JMenuItem menuitem_save = new JMenuItem("Save...", icon_save_m);
		menuitem_save.setActionCommand("Save");
		menuitem_save.addActionListener(this);
		menuFile.add(menuitem_save);
		menubar.add(menuFile);

		JMenu menuPen = new JMenu("Pen");//ペンの設定
		menuPen.setIcon(icon_pen_m);

		JMenu menuColor = new JMenu("Color");
		this.addMenuItem(menuColor,"Black","Black",this);
		this.addMenuItem(menuColor,"Blue","Blue",this);
		this.addMenuItem(menuColor,"Yellow","Yellow",this);
		this.addMenuItem(menuColor,"Green","Green",this);
		this.addMenuItem(menuColor,"Red","Red",this);
		this.addMenuItem(menuColor,"Rainbow","Rainbow",this);
		this.addMenuItem(menuColor,"Other...","Other",this);
		menuPen.add(menuColor);

		JMenu menuWidth = new JMenu("Width");
		this.addMenuItem(menuWidth, "width1", "width1", this);
		this.addMenuItem(menuWidth, "width5", "width5", this);
		this.addMenuItem(menuWidth, "width10", "width10", this);
		this.addMenuItem(menuWidth, "width20", "width20", this);
		menuPen.add(menuWidth);

		menubar.add(menuPen);//ペンの設定終わり

		JMenu menuEraser = new JMenu("Eraser");
		menuEraser.setIcon(icon_eraser_m);

		this.addMenuItem(menuEraser, "width1", "e_width1", this);
		this.addMenuItem(menuEraser, "width5", "e_width5", this);
		this.addMenuItem(menuEraser, "width10", "e_width10", this);
		this.addMenuItem(menuEraser, "width20", "e_width20", this);
		this.addMenuItem(menuEraser, "All Clear", "allclear", this);
		//menuEraser.add(menuWidth);
		menubar.add(menuEraser);

		JMenu menuShape = new JMenu("Shape");
		menuShape.setIcon(icon_stamp_m);
		//this.addMenuItem(menuShape, "Circle", "Circle", this);
		JMenuItem menuitem_circle = new JMenuItem("Circle", icon_circle_m);
		menuitem_circle.setActionCommand("Circle");
		menuitem_circle.addActionListener(this);
		menuShape.add(menuitem_circle);
		//this.addMenuItem(menuShape, "Square", "Square", this);
		JMenuItem menuitem_square = new JMenuItem("Square", icon_square_m);
		menuitem_square.setActionCommand("Square");
		menuitem_square.addActionListener(this);
		menuShape.add(menuitem_square);
		this.addMenuItem(menuShape, "Beimax", "Beimax", this);
		//this.addMenuItem(menuShape, "Original", "Original", this);
		JMenuItem menuitem_original = new JMenuItem("Original", icon_pic_m);
		menuitem_original.setActionCommand("Original");
		menuitem_original.addActionListener(this);
		menuShape.add(menuitem_original);

		menubar.add(menuShape);

		this.setJMenuBar(menubar);
	}




	private void init() {
	    this.setTitle("Paint");
	    this.setBounds(350, 20, 800, 600);
	    this.initMenu();

	    this.addMouseListener(this);
	    this.addMouseMotionListener(this);
	    panel=new DrawPanel();
	    panel.setLayout(null);
	    panel.setBounds(500, 500, 500, 300);
	    
	    /*JScrollPane scrollpane = new JScrollPane(panel);
	    scrollpane.setPreferredSize(new Dimension(800, 600));
	    JViewport view = scrollpane.getViewport();
	    view.setView(panel);
	    this.add(scrollpane);*/
	    
	    this.getContentPane().add(panel);

	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }

	public void init_toolMenu() {
		setLayout(new FlowLayout());//パネルの大きさの変更を有効にする

		pen_b = new JButton("Pen", icon_pen);
		//pen_b.setHorizontalTextPosition(JButton.LEFT);
		//pen_b.setVerticalTextPosition(JButton.CENTER);
		pen_b.setActionCommand("pen");
		pen_b.addActionListener(this);
		pen_b.setBounds(20, 10, 120, 80);
		pen_b.setForeground(Color.BLUE);

	    eraser_b = new JButton("Eraser", icon_eraser);
	    eraser_b.setActionCommand("eraser");
	    eraser_b.addActionListener(this);
	    eraser_b.setBounds(160, 10, 120, 80);

	    color_b = new JButton("Color", icon_color);
	    color_b.setActionCommand("Other");
	    color_b.addActionListener(this);
	    color_b.setBounds(20, 110, 120, 80);

	    spoit_b = new JButton("Dropper", icon_spoit);
	    spoit_b.setActionCommand("Spoit");
	    spoit_b.addActionListener(this);
	    spoit_b.setBounds(150, 110, 90, 80);

	    color_l = new JLabel();
	    //color_l.setPreferredSize(new Dimension(50, 50));
	    color_l.setOpaque(true);
	    color_l.setBackground(panel.currentColor);

	    color_l.setBounds(245, 135, 30, 30);
	    //color_l.setText("color");


	    JPanel p_write = new JPanel();

	    p_write.setLayout(null);
	    //p_write.setBackground(new Color(175,238,238));

	    //p.setLayout(new GridLayout(2,2));
	    p_write.setPreferredSize(new Dimension(300,200));
	    //JPanel Color_p = new JPanel();
	    p_write.add(pen_b);
	    p_write.add(eraser_b);
	    p_write.add(color_b);
	    p_write.add(spoit_b);
	    p_write.add(color_l);

	    JPanel p_width = new JPanel();
	    p_width.setBackground(new Color(173,216,230));
	    p_width.setLayout(new GridLayout(2,1));
	    p_width.setPreferredSize(new Dimension(300,100));

	    slider = new JSlider(0,50);
	    slider.setValue(10);
	    slider.setMajorTickSpacing(10);
	    slider.setMinorTickSpacing(1);

	    slider.setPaintTicks(true);

	    slider.setSnapToTicks(true);

	    slider.setLabelTable(slider.createStandardLabels(10));
	    slider.setPaintLabels(true);

	    slider.addChangeListener(this);

	    label = new JLabel();
	    label.setPreferredSize(new Dimension(300, 30));
	    label.setHorizontalAlignment(JLabel.CENTER);
	    label.setText("width：" + slider.getValue());

	    p_width.add(label);
	    p_width.add(slider);

	    moji_korokoro_b = new JButton("MojiKorokoro");
	    moji_korokoro_b.setActionCommand("Moji_korokoro");
	    moji_korokoro_b.addActionListener(this);
	    moji_korokoro_b.setBounds(160, 20, 120, 25);

	    moji_stamp_b = new JButton("MojiStamp");
	    moji_stamp_b.setActionCommand("Moji_stamp");
	    moji_stamp_b.addActionListener(this);
	    moji_stamp_b.setBounds(160, 50, 120, 25);

	    moji_stamp = new JTextField(20);
	    moji_stamp.setBounds(20, 35, 120, 30);
	    moji_stamp.setToolTipText("Please enter favorite sentence！");

	    radio1 = new JRadioButton("Normal");
	    radio2 = new JRadioButton("Italic");
	    radio3 = new JRadioButton("Bold");

	    ButtonGroup group = new ButtonGroup();
	    group.add(radio1);
	    group.add(radio2);
	    group.add(radio3);

	    radio1.setBounds(20, 80, 80, 30);
	    radio1.setFont(new Font("Serif" , Font.PLAIN , 16));
	    radio2.setBounds(120, 80, 80, 30);
	    radio2.setFont(new Font("Serif" , Font.ITALIC, 16));
	    radio3.setBounds(210, 80, 80, 30);
	    radio3.setFont(new Font("Serif" , Font.BOLD , 16));

	    circle_b = new JButton("Circle", icon_circle);
	    circle_b.setActionCommand("Circle");
	    circle_b.addActionListener(this);
	    circle_b.setBounds(20, 20, 120, 50);

	    square_b = new JButton("Square", icon_square);
	    square_b.setActionCommand("Square");
	    square_b.addActionListener(this);
	    //color.setPreferredSize(new Dimension(100, 60));
	    square_b.setBounds(160, 20, 120, 50);

	    beimax_b = new JButton("Beimax");
	    beimax_b.setActionCommand("Beimax");
	    beimax_b.addActionListener(this);
	    //color.setPreferredSize(new Dimension(100, 60));
	    beimax_b.setBounds(20, 80, 120, 50);


	    original_b = new JButton("Original", icon_pic);
	    original_b.setActionCommand("Original");
	    original_b.addActionListener(this);
	    //color.setPreferredSize(new Dimension(100, 60));
	    original_b.setBounds(160, 80, 120, 50);

	    JPanel p_stamp = new JPanel();
	    p_stamp.setLayout(null);

	    JPanel p_moji_stamp = new JPanel();
	    p_moji_stamp.setLayout(null);

	    //p.setLayout(new GridLayout(2,2));
	    p_moji_stamp.setPreferredSize(new Dimension(300,130));
	    p_moji_stamp.setBackground(new Color(176,224,230));
	    p_stamp.setPreferredSize(new Dimension(300,150));
	    p_stamp.setBackground(new Color(176,196,222));


	    //JPanel Color_p = new JPanel();
	    p_moji_stamp.add(moji_korokoro_b);
	    p_moji_stamp.add(moji_stamp_b);
	    p_moji_stamp.add(moji_stamp);
	    p_moji_stamp.add(radio1);
	    p_moji_stamp.add(radio2);
	    p_moji_stamp.add(radio3);

	    p_stamp.add(circle_b);
	    p_stamp.add(square_b);
	    p_stamp.add(beimax_b);
	    p_stamp.add(original_b);


	    Container container = this.getContentPane();
	    //container.add(p, BorderLayout.CENTER);
	    container.add(p_write, BorderLayout.CENTER);
	    container.add(p_width, BorderLayout.CENTER);
	    container.add(p_moji_stamp, BorderLayout.CENTER);
	    container.add(p_stamp, BorderLayout.CENTER);
	}


	private void init_tool() {
		this.setTitle("Tool");
	    this.setSize(300, 630);
	    this.init_toolMenu();

	    //panel=new DrawPanel();

	    this.addMouseListener(this);
	    this.addMouseMotionListener(this);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		SimpleDraw frame=new SimpleDraw();
	    frame.init();
	    SimpleDraw tool_window = new SimpleDraw();
        tool_window.init_tool();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "New") {//白紙にし、黒のペンにする
			panel.createBuffer(800, 600);
			panel.setPenColor(Color.black);
			color_l.setBackground(panel.currentColor);
			rainbow=false;
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);

			repaint();
		}
		if(e.getActionCommand() == "Open"){
			JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION){
		      File file = filechooser.getSelectedFile();
		      panel.openFile(file);
		    }else if (selected == JFileChooser.CANCEL_OPTION){
		      //label.setText("キャンセルされました");
		    }else if (selected == JFileChooser.ERROR_OPTION){
		      //label.setText("エラー又は取消しがありました");
		    }
		  }


		if(e.getActionCommand() == "Save"){
			JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showSaveDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION){
		      File file = filechooser.getSelectedFile();
		      //label.setText(file.getName());
		      //File file = new File("image.jpg");
		      panel.saveFile (file);
		    }else if (selected == JFileChooser.CANCEL_OPTION){
		      //label.setText("キャンセルされました");
		    }else if (selected == JFileChooser.ERROR_OPTION){
		      //label.setText("エラー又は取消しがありました");
		    }
		}

		if(e.getActionCommand() == "pen"){
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}

			pen_b.setBackground(Color.BLUE);

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "eraser"){
			panel.pen_after_eraser_Color=panel.currentColor;
			panel.setPenColor(Color.white);
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//eraser使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLUE);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		/*-------------太さ----------------------*/
		if(e.getActionCommand() == "width1"){
			panel.setPenWidth(1);
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "width5"){
			panel.setPenWidth(5);
			if(panel.currentColor==Color.white) {
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "width10"){
			panel.setPenWidth(10);
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "width20"){
			panel.setPenWidth(20);
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		/*---------------色---------------------*/
		if(e.getActionCommand() == "Black"){
			panel.setPenColor(Color.black);
			color_l.setBackground(panel.currentColor);
			rainbow=false;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Blue"){
			panel.setPenColor(Color.blue);
			color_l.setBackground(panel.currentColor);

			rainbow=false;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Yellow"){
			panel.setPenColor(Color.yellow);
			color_l.setBackground(panel.currentColor);

			rainbow=false;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Green"){
			panel.setPenColor(Color.green);
			color_l.setBackground(panel.currentColor);

			rainbow=false;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Red"){
			panel.setPenColor(Color.red);
			color_l.setBackground(panel.currentColor);

			rainbow=false;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Rainbow"){
			color_l.setBackground(panel.currentColor);
			rainbow=true;

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//pen使ってるとき
			pen_b.setForeground(Color.BLUE);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);

		}
		if(e.getActionCommand() == "Spoit"){
			rainbow=false;
			pen_bool=false;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;

			spoit=true;

			//spoit使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLUE);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}

		if(e.getActionCommand() == "Other"){
			JColorChooser colorchooser = new JColorChooser();

			if(panel.currentColor==Color.white){
				//pen使ってるとき
				pen_b.setForeground(Color.BLUE);
				eraser_b.setForeground(Color.BLACK);
				color_b.setForeground(Color.BLACK);
				spoit_b.setForeground(Color.BLACK);
				circle_b.setForeground(Color.BLACK);
				square_b.setForeground(Color.BLACK);
				beimax_b.setForeground(Color.BLACK);
				original_b.setForeground(Color.BLACK);
				moji_korokoro_b.setForeground(Color.BLACK);
				moji_stamp_b.setForeground(Color.BLACK);

				spoit=false;
				rainbow=false;
				pen_bool=true;
				circle_bool=false;
				square_bool=false;
				beima_bool=false;
				original_bool=false;
				moji_korokoro_bool=false;
				moji_stamp_bool=false;
			}

            Color color = JColorChooser.showDialog(this,"choose a color",panel.currentColor);

			panel.setPenColor(color);
			color_l.setBackground(panel.currentColor);




		}
		if(e.getActionCommand() == "e_width1"){
			panel.setPenWidth(1);
			panel.pen_after_eraser_Color=panel.currentColor;
			panel.setPenColor(Color.white);

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//eraser使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLUE);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "e_width5"){
			panel.setPenWidth(5);
			panel.pen_after_eraser_Color=panel.currentColor;
			panel.setPenColor(Color.white);
			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//eraser使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLUE);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "e_width10"){
			panel.setPenWidth(10);
			panel.pen_after_eraser_Color=panel.currentColor;
			panel.setPenColor(Color.white);

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//eraser使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLUE);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "e_width20"){
			panel.setPenWidth(20);
			panel.pen_after_eraser_Color=panel.currentColor;
			panel.setPenColor(Color.white);

			pen_bool=true;
			circle_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//eraser使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLUE);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "allclear") {
			panel.createBuffer(800, 600);
			color_l.setBackground(panel.currentColor);
			repaint();
		}

		//-------------------------Shape---------------------------
		if(e.getActionCommand() == "Circle"){
			//panel.drawCircle(lastx,lasty,click_x,click_y);
			circle_bool=true;
			pen_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//circle使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLUE);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Square"){
			circle_bool=false;
			pen_bool=false;
			square_bool=true;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//square使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLUE);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Beimax"){
			circle_bool=false;
			pen_bool=false;
			square_bool=false;
			beima_bool=true;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//beimax使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLUE);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Original"){
			JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION){
		      original_pic = filechooser.getSelectedFile();
		    }else if (selected == JFileChooser.CANCEL_OPTION){
		      //label.setText("キャンセルされました");
		    }else if (selected == JFileChooser.ERROR_OPTION){
		      //label.setText("エラー又は取消しがありました");
		    }

			circle_bool=false;
			pen_bool=false;
			square_bool=false;
			beima_bool=false;

			original_bool=true;

			moji_korokoro_bool=false;
			moji_stamp_bool=false;
			spoit=false;

			//original使ってるとき
			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLUE);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Moji_korokoro"){
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			circle_bool=false;
			pen_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=true;
			moji_stamp_bool=false;
			moji_s=moji_stamp.getText();
			//System.out.println(moji_s);
			spoit=false;

			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLUE);
			moji_stamp_b.setForeground(Color.BLACK);
		}
		if(e.getActionCommand() == "Moji_stamp"){
			if(panel.currentColor==Color.white){
				panel.setPenColor(panel.pen_after_eraser_Color);
			}
			circle_bool=false;
			pen_bool=false;
			square_bool=false;
			beima_bool=false;
			original_bool=false;
			moji_korokoro_bool=false;
			moji_stamp_bool=true;
			moji_s=moji_stamp.getText();

			spoit=false;

			pen_b.setForeground(Color.BLACK);
			eraser_b.setForeground(Color.BLACK);
			color_b.setForeground(Color.BLACK);
			spoit_b.setForeground(Color.BLACK);
			circle_b.setForeground(Color.BLACK);
			square_b.setForeground(Color.BLACK);
			beimax_b.setForeground(Color.BLACK);
			original_b.setForeground(Color.BLACK);
			moji_korokoro_b.setForeground(Color.BLACK);
			moji_stamp_b.setForeground(Color.BLUE);
		}
		 if (radio1.isSelected()){//normal
			 panel.font_num=0;
		 }
		 if (radio2.isSelected()){//Italic
			 panel.font_num=1;
		 }
		 if (radio3.isSelected()){//Bold
			 panel.font_num=2;
		 }

	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		label.setText("width：" + slider.getValue());
		panel.setPenWidth(slider.getValue());
	}
}



