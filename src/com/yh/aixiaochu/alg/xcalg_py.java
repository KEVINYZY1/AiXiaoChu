package com.yh.aixiaochu.alg;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * �����㷨�����㲽��
 * @author gudh
 *
 */
public class xcalg_py {

	public static List<ResBean> calculate_step(int[][] mat) {
		// '������һ�������п������//'
		int ws = mat.length;
		int hs = mat[0].length;

		List<ResBean> res = new ArrayList<ResBean>();
		// �������ϣ���������
		for (int j = 0; j < hs; j++) {
			int[] last = { -1, -1 };
			int[] last2 = { -1, -1 };
			for (int i = 0; i < ws; i++) {
				if (last[0] != -1 && last[1] != -1) {
					int m1 = mat[last[0]][last[1]];
					int m2 = mat[i][j];
					if (m1 == m2) {
						// ����
						List<ResBean> a = GetRound.get_arround( new int[] { i, j }, last, mat);
						res.addAll(a);
					} else if (last2[0] != -1 && last2[1] != -1) {
						// �м�
						int m0 = mat[last2[0]][last2[1]];
						if (m0 == m2) {
							List<ResBean> a = GetRound.get_middle( new int[] { i, j }, last2,mat);
							res.addAll(a);
						}
					}
					last2 = last;
					last = new int[] { i, j };
				} else {
					last2 = last;
					last = new int[] { i, j };
				}
			}
		}
		// �������󣬴�������
		for (int i = 0; i < ws; i++) {
			int[] last = { -1, -1 };
			int[] last2 = { -1, -1 };
			for (int j = 0; j < hs; j++) {
				if (last[0] != -1 && last[1] != -1) {
					int m1 = mat[last[0]][last[1]];
					int m2 = mat[i][j];
					if (m1 == m2) {
						// ����
						List<ResBean> a = GetRound.get_arround( new int[] { i, j }, last, mat);
						res.addAll(a);
					} else if (last2[0] != -1 && last2[1] != -1) {
						// �м�
						int m0 = mat[last2[0]][last2[1]];
						if (m0 == m2) {
							List<ResBean> a = GetRound.get_middle( new int[] { i, j }, last2,mat);
							res.addAll(a);
						}
					}
					last2 = last;
					last = new int[] { i, j };
				} else {
					last2 = last;
					last = new int[] { i, j };
				}
			}
		}
		if (res.size() > 20) {
			Log.i("Result", "PIC is ! ok, result count is bigger return no result "
							+ res.size());
			res = null;
		}
		return res;
	}

	public static boolean is_down_ok(HashMap<Integer, Integer> ys,
			ResBean needlocs) {
		// '�Ƿ������棬�����������Զ������//'
		for (int[] loc : needlocs.zhanyong) {
			if (ys.containsKey(loc[1]) && ys.get(loc[1]) >= loc[0]) {
				return false;
			}
		}
		return true;
	}

	public static void add_ys(HashMap<Integer, Integer> ys, ResBean needlocs) {
		// '��Ӻϲ�,����ͬy�����x����//'
		for (int[] loc : needlocs.zhanyong) {
			if (ys.containsKey(loc[1])) {
				if (ys.get(loc[1]) < loc[0]) {
					ys.put(loc[1], loc[0]);
				}
			} else {
				ys.put(loc[1], loc[0]);
			}
		}
	}

	@SuppressLint("UseSparseArrays")
	public static List<ResBean> get_optimal(List<ResBean> avails) {
		// '��ȡһ��ͼ���ŵĲ��輯����˳���//'
		List<ResBean> res = new ArrayList<ResBean>();
		HashMap<Integer, Integer> ys = new HashMap<Integer, Integer>();
		// �����ͬ�������
		// for (ResBean ava : avails){
		// if (avails.(ava) > 1){
		// if (!is_down_ok(ys, ava[2])){
		// continue
		// res.append(ava[0 : 2])
		// add_ys(ys, ava[2])
		// }
		// }
		for (ResBean ava : avails) {
			// ���xС�ڷ�Χ��������
			if (!is_down_ok(ys, ava)) {
				continue;
			}
			res.add(ava);
			add_ys(ys, ava);
		}
		return res;
	}

}
